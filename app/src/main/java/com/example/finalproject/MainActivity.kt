package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scard = findViewById<CardView>(R.id.scard)
        scard.setOnClickListener {
            val intent = Intent(this, SleepActivity::class.java)
            startActivity(intent)
        }

        val ascard = findViewById<CardView>(R.id.ascard)
        ascard.setOnClickListener {
            val intent1 = Intent(this, Acoustic::class.java)
            startActivity(intent1)
        }
        val citycard = findViewById<CardView>(R.id.citycard)
        citycard.setOnClickListener {
            val intent2 = Intent(this, city::class.java)
            startActivity(intent2)
        }
        val bcard = findViewById<CardView>(R.id.bcard)
        bcard.setOnClickListener {
            val intent3 = Intent(this, birds::class.java)
            startActivity(intent3)
        }
    }
}