package com.example.appbanco // Make sure this matches your package

import android.content.Intent
import android.graphics.Color
import java.math.BigDecimal
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.NumberFormat
import java.util.Locale

// Data class to hold transaction details (optional, but good for structure)
enum class TransactionType { SENT, RECEIVED, INVESTED, RETRIEVED_INVESTMENT }
data class Transaction(val message: String, val type: TransactionType)

class HomeActivity : AppCompatActivity() {

    private lateinit var tvSaldo: TextView
    private lateinit var btnSend: Button
    private lateinit var btnReceive: Button
    private lateinit var tvBemVindo: TextView
    private lateinit var llTransactionHistory: LinearLayout
    private lateinit var svHistoryScrollView: ScrollView // For scrolling to bottom

    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    private val MAX_DISPLAY_HISTORY_ITEMS = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val logoutButton = findViewById<Button>(R.id.bt_logout) // Make sure R.id.bt_logout is correct
        logoutButton.setOnClickListener {
            // --- RESET ALL USER DATA ---
            BalanceManager.resetAllUserData(this) // Call the reset method

            // Navigate to MainActivity (Login Screen)
            val intent = Intent(this, MainActivity::class.java)
            // Flags to clear the back stack and start MainActivity as a new task
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Finish HomeActivity
        }

        tvSaldo = findViewById(R.id.tvSaldo)
        btnSend = findViewById(R.id.btnSend)
        btnReceive = findViewById(R.id.btnReceive)
        tvBemVindo = findViewById(R.id.tvBemVindo)
        llTransactionHistory = findViewById(R.id.llTransactionHistory)
        // svHistoryScrollView = findViewById(R.id.history_scroll_view) // Get ScrollView if you used a specific ID for it.
        // For now, we'll get it from its child.

        val userName = intent.getStringExtra("USER_NAME_EXTRA")
        tvBemVindo.text = if (!userName.isNullOrEmpty()) "Olá, seja bem vindo(a) $userName" else "Olá, seja bem vindo(a)"

        updateBalanceDisplay()
        loadAndDisplayHomeScreenHistory()

        btnSend.setOnClickListener {
            showAmountDialog("Enviar Dinheiro") { amount ->
                val mainBalance = BalanceManager.getMainBalance(this)
                if (amount > BigDecimal.ZERO && amount <= mainBalance) {
                    if (BalanceManager.subtractFromMainBalance(this, amount)) {
                        updateBalanceDisplay()
                        val message = "Você enviou ${currencyFormat.format(amount)}"
                        val transaction = Transaction(message, TransactionType.SENT)
                        BalanceManager.addTransactionToHomeHistory(this, transaction) // Save to persistent storage
                        addTransactionToDisplay(transaction) // Update UI
                        Toast.makeText(this, "Enviado: ${currencyFormat.format(amount)}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Falha ao enviar. Tente novamente.", Toast.LENGTH_SHORT).show()
                    }
                } else if (amount > mainBalance) {
                    Toast.makeText(this, "Saldo principal insuficiente!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Valor inválido.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnReceive.setOnClickListener {
            showAmountDialog("Receber Dinheiro") { amount ->
                if (amount > BigDecimal.ZERO) {
                    BalanceManager.addToMainBalance(this, amount)
                    updateBalanceDisplay()
                    val message = "Você recebeu ${currencyFormat.format(amount)}"
                    val transaction = Transaction(message, TransactionType.RECEIVED)
                    BalanceManager.addTransactionToHomeHistory(this, transaction) // Save
                    addTransactionToDisplay(transaction) // Update UI
                    Toast.makeText(this, "Recebido: ${currencyFormat.format(amount)}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Valor inválido.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_navigation_container, BottomNavigationFragment())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        updateBalanceDisplay()
        loadAndDisplayHomeScreenHistory() // Reload history in case it changed (e.g., from InvestirActivity)
    }

    private fun updateBalanceDisplay() {
        val balance = BalanceManager.getMainBalance(this)
        tvSaldo.text = currencyFormat.format(balance)
    }

    private fun showAmountDialog(title: String, onConfirm: (BigDecimal) -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        input.hint = "Digite o valor"
        builder.setView(input)

        builder.setPositiveButton("Confirmar") { dialog, _ ->
            val amountText = input.text.toString()
            if (amountText.isNotEmpty()) {
                try {
                    val amount = BigDecimal(amountText) // Use BigDecimal
                    if (amount > BigDecimal.ZERO) {
                        onConfirm(amount)
                    } else {
                        Toast.makeText(this, "O valor deve ser positivo.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Valor inválido.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, insira um valor.", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun addTransactionToDisplay(transaction: Transaction) {
        if (llTransactionHistory.childCount >= MAX_DISPLAY_HISTORY_ITEMS) {
            if (llTransactionHistory.childCount > 0) {
                llTransactionHistory.removeViewAt(llTransactionHistory.childCount - 1) // Remove oldest from display
            }
        }
        val transactionView = TextView(this).apply {
            text = transaction.message
            textSize = 15f
            setPadding(0, 8, 0, 8)
            when (transaction.type) {
                TransactionType.SENT -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_sent_red))
                TransactionType.RECEIVED -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_received_green))
                TransactionType.INVESTED -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_invested_blue))
                TransactionType.RETRIEVED_INVESTMENT -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_retrieved_purple))
            }
        }
        llTransactionHistory.addView(transactionView, 0) // Add new one to the top of the display
        (llTransactionHistory.parent as? ScrollView)?.post {
            (llTransactionHistory.parent as ScrollView).fullScroll(ScrollView.FOCUS_UP)
        }
    }

    private fun loadAndDisplayHomeScreenHistory() {
        llTransactionHistory.removeAllViews()
        val history = BalanceManager.getHomeTransactionHistory(this)
        // Display up to MAX_DISPLAY_HISTORY_ITEMS
        history.take(MAX_DISPLAY_HISTORY_ITEMS).forEach { transaction ->
            // Note: getHomeTransactionHistory now returns newest first, so we don't need .reversed()
            val transactionView = TextView(this).apply {
                text = transaction.message
                textSize = 15f
                setPadding(0, 8, 0, 8)
                when (transaction.type) {
                    TransactionType.SENT -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_sent_red))
                    TransactionType.RECEIVED -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_received_green))
                    TransactionType.INVESTED -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_invested_blue)) // Or your preferred color
                    TransactionType.RETRIEVED_INVESTMENT -> setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.transaction_retrieved_purple)) // Or your preferred color
                }
            }
            llTransactionHistory.addView(transactionView) // Add to bottom as we iterate (newest is first in list)
        }
        (llTransactionHistory.parent as? ScrollView)?.post {
            (llTransactionHistory.parent as ScrollView).fullScroll(ScrollView.FOCUS_UP)
        }
    }
}