package com.example.appbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.ui.semantics.error
//import androidx.compose.ui.semantics.text
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.text.isEmpty
import kotlin.text.trim

class MainActivity : AppCompatActivity() {

    // Declare your views
    private lateinit var etNome: EditText
    private lateinit var btEntrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure the layout name here matches your actual layout file for MainActivity
        setContentView(R.layout.activity_main) // Or your main activity's layout file

        // Initialize your views
        // Replace R.id.etNome and R.id.bt_entrar with the actual IDs from your MainActivity layout
        etNome = findViewById(R.id.etNome)
        btEntrar = findViewById(R.id.bt_entrar)

        btEntrar.setOnClickListener {
            val userName = etNome.text.toString().trim()

            // Optional: You might want to add validation to ensure the user entered a name
            if (userName.isEmpty()) {
                etNome.error = "Please enter your name"
                return@setOnClickListener // Don't proceed if the name is empty
            }

            val intent = Intent(this, HomeActivity::class.java) // Or Home::class.java
            // Add the username as an extra to the intent
            // We'll use "USER_NAME_EXTRA" as the key to retrieve it in HomeActivity
            intent.putExtra("USER_NAME_EXTRA", userName)
            startActivity(intent)
        }
    }
}