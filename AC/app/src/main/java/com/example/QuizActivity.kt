package com.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.ac.R

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val btResp1 = findViewById<Button>(R.id.bt_resp1)
        val btResp2 = findViewById<Button>(R.id.bt_resp2)
        val btResp3 = findViewById<Button>(R.id.bt_resp3)

        btResp1.setOnClickListener {
            abrirHeroiActivity("heroi1")
        }

        btResp2.setOnClickListener {
            abrirHeroiActivity("heroi2")
        }

        btResp3.setOnClickListener {
            abrirHeroiActivity("heroi3")
        }
    }

    private fun abrirHeroiActivity(heroi: String) {
        val intent = Intent(this, HeroiActivity::class.java)
        intent.putExtra("heroi_escolhido", heroi)
        startActivity(intent)
    }
}