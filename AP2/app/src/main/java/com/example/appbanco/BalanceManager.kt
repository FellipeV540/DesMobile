package com.example.appbanco // Your package

import android.content.Context
import android.content.SharedPreferences
//import androidx.compose.ui.input.key.type
import com.google.gson.Gson // For JSON serialization
import com.google.gson.reflect.TypeToken // For JSON deserialization
import java.math.BigDecimal

// Make sure your data classes are accessible or redefined here if not in the same package
// It's often better to have these in a common data model package if used across modules
// For simplicity, I'll assume they are available or you can copy/paste their definitions here.
// data class Transaction(val message: String, val type: TransactionType)
// enum class TransactionType { SENT, RECEIVED, INVESTED, RETRIEVED_INVESTMENT } // Expanded for home
// data class InvestmentTransaction(val message: String, val type: InvestmentTransactionType)
// enum class InvestmentTransactionType { INVESTED, RETRIEVED }


object BalanceManager {
    private const val PREFS_NAME = "AppBancoPrefs"
    private const val KEY_MAIN_BALANCE = "mainBalance"
    private const val KEY_INVESTED_BALANCE = "investedBalance"
    private const val KEY_HOME_TRANSACTION_HISTORY = "homeTransactionHistory"
    private const val KEY_INVESTMENT_TRANSACTION_HISTORY = "investmentTransactionHistory"
    private const val MAX_HISTORY_ITEMS_PERSISTENCE = 50 // Max items to store in SharedPreferences
    private const val INVESTIR_ACTIVITY_PREFS_NAME = "InvestirActivityPrefs" // From previous example
    private const val KEY_INVESTOR_TYPE = "savedInvestorType"

    private val gson = Gson() // For serializing/deserializing lists

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // --- Balances (as before) ---
    fun getMainBalance(context: Context): BigDecimal {
        val balanceStr = getPreferences(context).getString(KEY_MAIN_BALANCE, "0.0")
        return BigDecimal(balanceStr ?: "0.0") // Handle null string just in case
    }

    fun setMainBalance(context: Context, balance: BigDecimal) {
        getPreferences(context).edit().putString(KEY_MAIN_BALANCE, balance.toPlainString()).apply()
    }

    fun addToMainBalance(context: Context, amount: BigDecimal) {
        val current = getMainBalance(context)
        setMainBalance(context, current.add(amount))
    }

    fun subtractFromMainBalance(context: Context, amount: BigDecimal): Boolean {
        val current = getMainBalance(context)
        return if (current >= amount) {
            setMainBalance(context, current.subtract(amount))
            true
        } else {
            false
        }
    }

    fun getInvestedBalance(context: Context): BigDecimal {
        val balanceStr = getPreferences(context).getString(KEY_INVESTED_BALANCE, "0.0")
        return BigDecimal(balanceStr ?: "0.0")
    }

    fun setInvestedBalance(context: Context, balance: BigDecimal) {
        getPreferences(context).edit().putString(KEY_INVESTED_BALANCE, balance.toPlainString()).apply()
    }

    fun addToInvestedBalance(context: Context, amount: BigDecimal) {
        val current = getInvestedBalance(context)
        setInvestedBalance(context, current.add(amount))
    }

    fun subtractFromInvestedBalance(context: Context, amount: BigDecimal): Boolean {
        val current = getInvestedBalance(context)
        return if (current >= amount) {
            setInvestedBalance(context, current.subtract(amount))
            true
        } else {
            false
        }
    }

    // --- Home Screen Transaction History ---
    fun getHomeTransactionHistory(context: Context): MutableList<Transaction> {
        val json = getPreferences(context).getString(KEY_HOME_TRANSACTION_HISTORY, null)
        return if (json != null) {
            val type = object : com.google.gson.reflect.TypeToken<MutableList<Transaction>>() {}.type
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    fun addTransactionToHomeHistory(context: Context, transaction: Transaction) {
        val history = getHomeTransactionHistory(context)
        history.add(0, transaction) // Add to the beginning (newest first)
        while (history.size > MAX_HISTORY_ITEMS_PERSISTENCE) {
            history.removeAt(history.size - 1) // Remove the oldest
        }
        val json = gson.toJson(history)
        getPreferences(context).edit().putString(KEY_HOME_TRANSACTION_HISTORY, json).apply()
    }

    // --- Investment Screen Transaction History ---
    fun getInvestmentTransactionHistory(context: Context): MutableList<InvestmentTransaction> {
        val json = getPreferences(context).getString(KEY_INVESTMENT_TRANSACTION_HISTORY, null)
        return if (json != null) {
            val type = object : com.google.gson.reflect.TypeToken<MutableList<InvestmentTransaction>>() {}.type
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    fun addTransactionToInvestmentHistoryStorage(context: Context, transaction: InvestmentTransaction) {
        val history = getInvestmentTransactionHistory(context)
        history.add(0, transaction) // Add to the beginning (newest first)
        while (history.size > MAX_HISTORY_ITEMS_PERSISTENCE) {
            history.removeAt(history.size - 1) // Remove the oldest
        }
        val json = gson.toJson(history)
        getPreferences(context).edit().putString(KEY_INVESTMENT_TRANSACTION_HISTORY, json).apply()
    }

    fun resetAllUserData(context: Context) {
        val editor = getPreferences(context).edit()

        editor.remove(KEY_MAIN_BALANCE)
        editor.remove(KEY_INVESTED_BALANCE)
        editor.remove(KEY_HOME_TRANSACTION_HISTORY)
        editor.remove(KEY_INVESTMENT_TRANSACTION_HISTORY)
        // Add any other keys related to user data that are stored in "AppBancoPrefs"

        editor.apply() // Apply the removals

        // If Investor Type is stored in a separate SharedPreferences file as per previous example:
        val investirPrefs = context.getSharedPreferences(INVESTIR_ACTIVITY_PREFS_NAME, Context.MODE_PRIVATE)
        investirPrefs.edit().remove(KEY_INVESTOR_TYPE).apply()
    }
}