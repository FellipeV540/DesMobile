package com.example.appbanco

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
//import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.ui.semantics.text
//import androidx.compose.ui.semantics.text
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appbanco.BottomNavigationFragment

class InvestirActivity : AppCompatActivity() {

    private lateinit var tvInvestedBalance: TextView
    private lateinit var btnInvest: Button
    private lateinit var btnRetrieve: Button
    private lateinit var tvInvestorTypeLabel: TextView
    private lateinit var btnDiscoverInvestorType: Button

    private var currentInvestorType: String? = null
    private var hasTakenInvestorTest: Boolean = false // Flag to track if test was taken

    // ActivityResultLauncher for getting the result from InvestorProfileActivity
    private lateinit var investorProfileLauncher: ActivityResultLauncher<Intent>

    // Define keys for saving instance state
    companion object {
        private const val KEY_INVESTOR_TYPE = "investor_type"
        private const val KEY_HAS_TAKEN_TEST = "has_taken_test"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investir)

        // Restore instance state
        if (savedInstanceState != null) {
            currentInvestorType = savedInstanceState.getString(KEY_INVESTOR_TYPE)
            hasTakenInvestorTest = savedInstanceState.getBoolean(KEY_HAS_TAKEN_TEST, false)
        }


        tvInvestedBalance = findViewById(R.id.tvInvestedBalance)
        btnInvest = findViewById(R.id.btnInvest)
        btnRetrieve = findViewById(R.id.btnRetrieve)
        tvInvestorTypeLabel = findViewById(R.id.tvInvestorTypeLabel)
        btnDiscoverInvestorType = findViewById(R.id.btnDiscoverInvestorType)

        // Initialize the ActivityResultLauncher
        investorProfileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.getStringExtra(InvestorProfileActivity.EXTRA_INVESTOR_TYPE)?.let { type ->
                    setInvestorType(type)
                    hasTakenInvestorTest = true // Mark that test has been taken and submitted
                    updateDiscoverButtonText()
                }
            } else {
                // Handle cancellation or other results if needed
                // For example, if the user presses back without submitting
            }
        }

        updateInvestorTypeLabel()
        updateDiscoverButtonText()

        btnDiscoverInvestorType.setOnClickListener {
            val intent = Intent(this, InvestorProfileActivity::class.java)
            investorProfileLauncher.launch(intent)
            overridePendingTransition(0, 0) // Optional: no animation for launching
        }

        if (savedInstanceState == null && supportFragmentManager.findFragmentById(R.id.bottom_navigation_container) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_navigation_container, BottomNavigationFragment())
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_INVESTOR_TYPE, currentInvestorType)
        outState.putBoolean(KEY_HAS_TAKEN_TEST, hasTakenInvestorTest)
    }


    private fun updateInvestorTypeLabel() {
        if (currentInvestorType != null) {
            tvInvestorTypeLabel.text = "Seu tipo de investidor é $currentInvestorType"
        } else {
            tvInvestorTypeLabel.text = "Seu tipo de investidor é ?"
        }
    }

    private fun updateDiscoverButtonText() {
        if (hasTakenInvestorTest) {
            btnDiscoverInvestorType.text = "Clique aqui para refazer o teste de tipo de investidor"
        } else {
            btnDiscoverInvestorType.text = "Clique aqui para descobrir que tipo de investidor você é"
        }
    }

    private fun setInvestorType(type: String) {
        currentInvestorType = type
        updateInvestorTypeLabel()
    }

    override fun onResume() {
        super.onResume()
        // Could also update texts here if data might change while activity is paused
        // but ActivityResultLauncher is more direct for this specific flow.
        updateInvestorTypeLabel()
        updateDiscoverButtonText()
    }
}