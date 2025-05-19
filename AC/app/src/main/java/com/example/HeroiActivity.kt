package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ac.R

class HeroiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroi)

        val imageView = findViewById<ImageView>(R.id.img_heroi)
        val textView = findViewById<TextView>(R.id.tv_heroi)
        val botaoRefazer = findViewById<Button>(R.id.bt_refazer)
        val botaoSaberMais = findViewById<Button>(R.id.bt_sabmais)

        val heroiEscolhido = intent.getStringExtra("heroi_escolhido")

        var urlParaAbrir: String? = null

        when (heroiEscolhido) {
            "heroi1" -> {
                imageView.setImageResource(R.drawable.flash)
                textView.text = "Você é o The Flash"
                urlParaAbrir = "https://pt.m.wikipedia.org/wiki/Flash_(DC_Comics)"
            }
            "heroi2" -> {
                imageView.setImageResource(R.drawable.superman)
                textView.text = "Você é o SuperMan"
                urlParaAbrir = "https://pt.m.wikipedia.org/wiki/Superman"
            }
            "heroi3" -> {
                imageView.setImageResource(R.drawable.batman)
                textView.text = "Você é o batman"
                urlParaAbrir = "https://pt.m.wikipedia.org/wiki/Batman"
            }
        }

        botaoRefazer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        botaoSaberMais.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(urlParaAbrir)
            startActivity(intent)
        })
    }
}