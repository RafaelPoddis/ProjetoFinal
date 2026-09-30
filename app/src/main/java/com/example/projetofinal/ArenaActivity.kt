package com.example.projetofinal

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity

class ArenaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_arena)

        val giveUp = findViewById<Button>(R.id.giveUpBtn)

        giveUp.setOnClickListener {
            finish()
        }

        val attack = findViewById<ImageButton>(R.id.atkBtn)
        val scoreTxt = findViewById<TextView>(R.id.scoreTxt)
        var score: Int = 0

        attack.setOnClickListener {
            score++
            scoreTxt.text = "Pontuação $score"
        }
    }
}