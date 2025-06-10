package com.example.appbanco // Replace with your actual package name

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView // Import TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
// import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL // Keep if you use it
import androidx.biometric.BiometricPrompt
//import androidx.compose.ui.semantics.error
//import androidx.compose.ui.semantics.text
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

// Make sure to import your HomeActivity
// import com.example.yourapp.HomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var btEntrar: Button
    private lateinit var tvSupportButton: TextView // Declare the support TextView

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private var userNameForAuth: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNome = findViewById(R.id.etNome)
        btEntrar = findViewById(R.id.bt_entrar)
        tvSupportButton = findViewById(R.id.tvSupportButton) // Initialize the support TextView

        executor = ContextCompat.getMainExecutor(this)
        setupBiometricPrompt()

        btEntrar.setOnClickListener {
            val userName = etNome.text.toString().trim()
            if (userName.isEmpty()) {
                etNome.error = "Por favor, insira seu nome"
                return@setOnClickListener
            }
            userNameForAuth = userName
            checkBiometricSupportAndAuthenticate()
        }

        tvSupportButton.setOnClickListener {
            sendSupportEmail()
        }
    }

    private fun sendSupportEmail() {
        val recipient = "fellipevalladares@gmail.com"
        val subject = "Suporte VallaBank App" // Optional: pre-fill subject
        // val body = "Olá, preciso de ajuda com..." // Optional: pre-fill body

        // Intent to send email
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            // putExtra(Intent.EXTRA_TEXT, body) // Uncomment to pre-fill body
        }

        try {
            startActivity(Intent.createChooser(intent, "Enviar email usando..."))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Nenhum app de email encontrado.", Toast.LENGTH_SHORT).show()
            Log.e("SUPPORT_EMAIL", "No email client found", e)
        }
    }

    private fun setupBiometricPrompt() {
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Erro de autenticação: $errString", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("BIOMETRIC", "Authentication error: $errorCode - $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Autenticação bem-sucedida!", Toast.LENGTH_SHORT)
                        .show()
                    navigateToHome()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Falha na autenticação.",
                        Toast.LENGTH_SHORT)
                        .show()
                    Log.e("BIOMETRIC", "Authentication failed.")
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticação Biométrica")
            .setSubtitle("Use sua impressão digital para continuar")
            .setNegativeButtonText("Cancelar")
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .build()
    }

    private fun checkBiometricSupportAndAuthenticate() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d("BIOMETRIC", "App can authenticate using biometrics.")
                biometricPrompt.authenticate(promptInfo)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.e("BIOMETRIC", "No biometric features available on this device.")
                Toast.makeText(this, "Hardware biométrico não disponível.", Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.e("BIOMETRIC", "Biometric features are currently unavailable.")
                Toast.makeText(this, "Recursos biométricos indisponíveis no momento.", Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Log.e("BIOMETRIC", "The user hasn't associated any biometric credentials with their account.")
                Toast.makeText(this, "Nenhuma digital cadastrada. Por favor, configure uma digital nas configurações do seu aparelho.", Toast.LENGTH_LONG).show()
            }
            // ... (other biometric error cases from previous implementation) ...
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                Log.e("BIOMETRIC", "Biometric security update required.")
                Toast.makeText(this, "Atualização de segurança biométrica necessária.", Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                Log.e("BIOMETRIC", "Biometric option is unsupported.")
                Toast.makeText(this, "Opção biométrica não suportada.", Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                Log.e("BIOMETRIC", "Biometric status unknown.")
                Toast.makeText(this, "Status biométrico desconhecido.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToHome() {
        BalanceManager.resetAllUserData(this)
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("USER_NAME_EXTRA", userNameForAuth)
        startActivity(intent)
        finish()
    }
}