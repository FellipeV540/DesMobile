package com.example.passagemdados

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, Fragmento())
            .commit()

        val tvNome = findViewById<TextView>(R.id.textView)
        val nomeInformado = intent.getStringExtra("Nome")
        val btFinalizar = findViewById<Button>(R.id.button2)
        tvNome.text = "Bem-vindo(a) " + nomeInformado

        btFinalizar.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)


        }
    }
}