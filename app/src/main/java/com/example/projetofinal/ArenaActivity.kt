package com.example.projetofinal

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class ArenaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_arena)

        val giveUp = findViewById<Button>(R.id.giveUpBtn)

        giveUp.setOnClickListener {
            finish()
        }
    }
}