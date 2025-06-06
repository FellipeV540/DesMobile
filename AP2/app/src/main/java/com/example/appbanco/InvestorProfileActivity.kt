package com.example.appbanco // Or your package name

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar

class InvestorProfileActivity : AppCompatActivity() {

    // Define a companion object for the result key
    companion object {
        const val EXTRA_INVESTOR_TYPE = "com.example.appbanco.EXTRA_INVESTOR_TYPE"
    }

    private lateinit var rgInvestorTypes: RadioGroup
    private lateinit var btnSubmitProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investor_profile)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar_investor_profile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            // User pressed back arrow, treat as cancel, send no result or a specific cancel result
            setResult(Activity.RESULT_CANCELED)
            finish()
            overridePendingTransition(0,0) // Optional: remove animation
        }

        rgInvestorTypes = findViewById(R.id.rgInvestorTypes)
        btnSubmitProfile = findViewById(R.id.btnSubmitProfile)

        btnSubmitProfile.setOnClickListener {
            val selectedOptionId = rgInvestorTypes.checkedRadioButtonId
            if (selectedOptionId == -1) {
                Toast.makeText(this, "Por favor, selecione uma opção.", Toast.LENGTH_SHORT).show()
            } else {
                val investorType = when (selectedOptionId) {
                    R.id.rbOptionConservative -> "Conservador"
                    R.id.rbOptionModerate -> "Moderado"
                    R.id.rbOptionAggressive -> "Arrojado"
                    else -> "Não definido"
                }

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_INVESTOR_TYPE, investorType)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
                overridePendingTransition(0,0) // Optional: remove animation
            }
        }
    }

    // Handle the Toolbar's Up button press if you haven't set a specific
    // toolbar.setNavigationOnClickListener that calls finish() or onBackPressedDispatcher
    // This ensures RESULT_CANCELED is sent if the user navigates up without submitting.
    override fun onSupportNavigateUp(): Boolean {
        setResult(Activity.RESULT_CANCELED) // Or handle as you see fit
        finish()
        overridePendingTransition(0,0) // Optional
        return true
    }

    // Override onBackPressed to also set result canceled if user presses system back
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
        overridePendingTransition(0,0) // Optional
    }
}