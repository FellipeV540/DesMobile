package com.example.appbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.ui.semantics.text

class HomeActivity : AppCompatActivity() { // Or class Home : AppCompatActivity()

    private lateinit var tvBemVindo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure the layout name here matches your actual layout file for HomeActivity
        setContentView(R.layout.activity_home) // Or R.layout.home if that's the layout file

        tvBemVindo = findViewById(R.id.tvBemVindo) // Ensure this ID matches your layout

        val LogoutButton = findViewById<Button>(R.id.bt_logout)
        LogoutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Retrieve the name passed from MainActivity
        val userName = intent.getStringExtra("USER_NAME_EXTRA")

        // Update the TextView
        if (userName != null && userName.isNotEmpty()) {
            tvBemVindo.text = "Olá, seja bem vindo(a) $userName"
        } else {
            // Fallback text if no name was passed or it's empty
            tvBemVindo.text = "Olá, seja bem vindo(a)!"
        }

        // Load your BottomNavigationFragment if you haven't already
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_navigation_container, BottomNavigationFragment())
                .commit()
        }
    }
}