package com.example.appbanco // Your package

import android.graphics.Color
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.math.BigDecimal // Use BigDecimal
import java.text.NumberFormat
import java.util.Locale

// You can reuse Transaction and TransactionType or create specific ones if needed
// For this example, let's add new types for investment
enum class InvestmentTransactionType { INVESTED, RETRIEVED }
data class InvestmentTransaction(val message: String, val type: InvestmentTransactionType)


class InvestirActivity : AppCompatActivity() {

    private lateinit var tvInvestedBalance: TextView
    private lateinit var btnInvest: Button
    private lateinit var btnRetrieve: Button
    private lateinit var llInvestmentTransactionHistory: LinearLayout
    private lateinit var btnDiscoverInvestorType: Button
    private lateinit var tvInvestorTypeLabel: TextView

    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    private val MAX_DISPLAY_INVESTMENT_HISTORY_ITEMS = 20

    companion object { // Use a companion object for request codes and intent extras
        private const val REQUEST_CODE_INVESTOR_PROFILE = 101
        const val EXTRA_INVESTOR_TYPE = "com.example.appbanco.EXTRA_INVESTOR_TYPE" // For InvestorProfileActivity to send data back
        private const val PREF_KEY_INVESTOR_TYPE = "savedInvestorType" // For SharedPreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investir)

        tvInvestedBalance = findViewById(R.id.tvInvestedBalance)
        btnInvest = findViewById(R.id.btnInvest)
        btnRetrieve = findViewById(R.id.btnRetrieve)
        llInvestmentTransactionHistory = findViewById(R.id.llInvestmentTransactionHistory)
        btnDiscoverInvestorType = findViewById(R.id.btnDiscoverInvestorType)
        tvInvestorTypeLabel = findViewById(R.id.tvInvestorTypeLabel)

        updateInvestedBalanceDisplay()
        loadAndDisplayInvestmentHistory() // Load and display any persisted history
        loadAndDisplaySavedInvestorType()

        btnInvest.setOnClickListener {
            showInvestmentAmountDialog("Investir Dinheiro") { amountToInvest ->
                val currentMainBalance = BalanceManager.getMainBalance(this)
                if (amountToInvest > BigDecimal.ZERO && amountToInvest <= currentMainBalance) {
                    if (BalanceManager.subtractFromMainBalance(this, amountToInvest)) {
                        BalanceManager.addToInvestedBalance(this, amountToInvest)
                        updateInvestedBalanceDisplay()

                        // For Investment Screen History
                        val investmentMessage = "Você investiu ${currencyFormat.format(amountToInvest)}"
                        val investmentTx = InvestmentTransaction(investmentMessage, InvestmentTransactionType.INVESTED)
                        BalanceManager.addTransactionToInvestmentHistoryStorage(this, investmentTx) // Save
                        addInvestmentTransactionToDisplay(investmentTx) // Update UI

                        // For Home Screen History (unified)
                        val homeMessage = "Você investiu ${currencyFormat.format(amountToInvest)}"
                        val homeTx = Transaction(homeMessage, TransactionType.INVESTED) // Use the expanded enum
                        BalanceManager.addTransactionToHomeHistory(this, homeTx)

                        Toast.makeText(this, "Investimento realizado!", Toast.LENGTH_SHORT).show()
                    } else {
                        // Should not happen if first check passes, but good for safety
                        Toast.makeText(this, "Falha ao debitar da conta principal.", Toast.LENGTH_SHORT).show()
                    }
                } else if (amountToInvest > currentMainBalance) {
                    Toast.makeText(this, "Saldo na conta principal insuficiente!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Valor de investimento inválido.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnRetrieve.setOnClickListener {
            showInvestmentAmountDialog("Resgatar Investimento") { amountToRetrieve ->
                val currentInvestedBalance = BalanceManager.getInvestedBalance(this)
                if (amountToRetrieve > BigDecimal.ZERO && amountToRetrieve <= currentInvestedBalance) {
                    if (BalanceManager.subtractFromInvestedBalance(this, amountToRetrieve)) {
                        BalanceManager.addToMainBalance(this, amountToRetrieve)
                        updateInvestedBalanceDisplay()

                        // For Investment Screen History
                        val investmentMessage = "Você resgatou ${currencyFormat.format(amountToRetrieve)} do investimento"
                        val investmentTx = InvestmentTransaction(investmentMessage, InvestmentTransactionType.RETRIEVED)
                        BalanceManager.addTransactionToInvestmentHistoryStorage(this, investmentTx) // Save
                        addInvestmentTransactionToDisplay(investmentTx) // Update UI

                        // For Home Screen History (unified)
                        val homeMessage = "Você resgatou ${currencyFormat.format(amountToRetrieve)} do investimento"
                        val homeTx = Transaction(homeMessage, TransactionType.RETRIEVED_INVESTMENT)
                        BalanceManager.addTransactionToHomeHistory(this, homeTx)

                        Toast.makeText(this, "Resgate realizado!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Falha ao resgatar. Tente novamente.", Toast.LENGTH_SHORT).show()
                    }
                } else if (amountToRetrieve > currentInvestedBalance) {
                    Toast.makeText(this, "Saldo investido insuficiente!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Valor de resgate inválido.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnDiscoverInvestorType.setOnClickListener {
            val intent = Intent(this, InvestorProfileActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_INVESTOR_PROFILE) // <-- Start for result
        }

        // --- Load Bottom Navigation Fragment ---
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_navigation_container_invest, BottomNavigationFragment()) // Use correct container ID
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        updateInvestedBalanceDisplay()
        loadAndDisplayInvestmentHistory() // Reload from persistence
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_INVESTOR_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {
                val investorType = data?.getStringExtra(EXTRA_INVESTOR_TYPE)
                if (!investorType.isNullOrEmpty()) {
                    tvInvestorTypeLabel.text = "Seu tipo de investidor é: $investorType"
                    saveInvestorType(investorType) // Save the determined type
                    Toast.makeText(this, "Perfil de investidor atualizado!", Toast.LENGTH_SHORT).show()
                } else {
                    tvInvestorTypeLabel.text = "Seu tipo de investidor é ?" // Reset or default
                }
            } else {
                // Handle cancellation or failure if needed
                // Toast.makeText(this, "Perfil não definido.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveInvestorType(investorType: String) {
        // Using SharedPreferences directly here for simplicity for this specific item.
        // You could also integrate this into BalanceManager if you prefer.
        val sharedPrefs = getSharedPreferences("InvestirActivityPrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putString(PREF_KEY_INVESTOR_TYPE, investorType).apply()
    }

    private fun loadAndDisplaySavedInvestorType() {
        val sharedPrefs = getSharedPreferences("InvestirActivityPrefs", Context.MODE_PRIVATE)
        val savedType = sharedPrefs.getString(PREF_KEY_INVESTOR_TYPE, null)
        if (!savedType.isNullOrEmpty()) {
            tvInvestorTypeLabel.text = "Seu tipo de investidor é: $savedType"
        } else {
            tvInvestorTypeLabel.text = "Seu tipo de investidor é ?" // Default text
        }
    }

    private fun updateInvestedBalanceDisplay() {
        val investedBalance = BalanceManager.getInvestedBalance(this)
        tvInvestedBalance.text = currencyFormat.format(investedBalance)
    }

    private fun loadAndDisplayInvestmentHistory() {
        llInvestmentTransactionHistory.removeAllViews()
        val history = BalanceManager.getInvestmentTransactionHistory(this)
        history.take(MAX_DISPLAY_INVESTMENT_HISTORY_ITEMS).forEach { transaction ->
            val transactionView = TextView(this).apply {
                text = transaction.message
                textSize = 15f
                setPadding(0, 8, 0, 8)
                when (transaction.type) {
                    InvestmentTransactionType.INVESTED -> setTextColor(ContextCompat.getColor(this@InvestirActivity, R.color.transaction_sent_red))
                    InvestmentTransactionType.RETRIEVED -> setTextColor(ContextCompat.getColor(this@InvestirActivity, R.color.transaction_received_green))
                }
            }
            llInvestmentTransactionHistory.addView(transactionView)
        }
        (llInvestmentTransactionHistory.parent as? ScrollView)?.post {
            (llInvestmentTransactionHistory.parent as ScrollView).fullScroll(ScrollView.FOCUS_UP)
        }
    }

    private fun addInvestmentTransactionToDisplay(transaction: InvestmentTransaction) {
        if (llInvestmentTransactionHistory.childCount >= MAX_DISPLAY_INVESTMENT_HISTORY_ITEMS) {
            if (llInvestmentTransactionHistory.childCount > 0) {
                llInvestmentTransactionHistory.removeViewAt(llInvestmentTransactionHistory.childCount - 1) // Remove oldest from display
            }
        }
        val transactionView = TextView(this).apply {
            text = transaction.message
            textSize = 15f
            setPadding(0, 8, 0, 8)
            when (transaction.type) {
                InvestmentTransactionType.INVESTED -> setTextColor(ContextCompat.getColor(this@InvestirActivity, R.color.transaction_sent_red))
                InvestmentTransactionType.RETRIEVED -> setTextColor(ContextCompat.getColor(this@InvestirActivity, R.color.transaction_received_green))
            }
        }
        llInvestmentTransactionHistory.addView(transactionView, 0) // Add new one to the top
        (llInvestmentTransactionHistory.parent as? ScrollView)?.post {
            (llInvestmentTransactionHistory.parent as ScrollView).fullScroll(ScrollView.FOCUS_UP)
        }
    }

    // Dialog for getting investment/retrieval amount
    private fun showInvestmentAmountDialog(title: String, onConfirm: (BigDecimal) -> Unit) {
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
}