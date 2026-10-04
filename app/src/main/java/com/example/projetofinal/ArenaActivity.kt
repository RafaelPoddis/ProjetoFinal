package com.example.projetofinal

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random

class ArenaActivity : ComponentActivity() {

    private var vidaJogador: Int = 50
    private var vidaInimigo: Int = 60
    private val chanceCritico: Float = 0.25f
    private val multiplicadorCritico: Float = 1.5f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_arena)

        val giveUp = findViewById<Button>(R.id.giveUpBtn)

        giveUp.setOnClickListener {
            finish()
        }

        val armaNome: String = intent.getStringExtra("armaNome") ?: "Punhos"
        val armaDano: Int = intent.getIntExtra("armaDano", 5)

        val itemTxt = findViewById<TextView>(R.id.itemTxt)
        val enemyHpTxt = findViewById<TextView>(R.id.enemyHpTxt)
        val playerHpTxt = findViewById<TextView>(R.id.playerHpTxt)
        val logTxt = findViewById<TextView>(R.id.logTxt)
        val attack = findViewById<ImageButton>(R.id.atkBtn)

        fun atualizarVidas() {
            enemyHpTxt.text = "Inimigo: $vidaInimigo HP"
            playerHpTxt.text = "Você: $vidaJogador HP"
        }
        atualizarVidas()

        itemTxt.text = "Arma: $armaNome"

        attack.setOnClickListener {
            val critico: Boolean = Random.nextFloat() < chanceCritico
            val dano: Int = if (critico) (armaDano * multiplicadorCritico).toInt() else armaDano
            vidaInimigo -= dano
            var log: String = if (critico) "CRÍTICO! Você causou $dano de dano." else "Você causou $dano de dano."

            if (vidaInimigo <= 0) {
                vidaInimigo = 0
                atualizarVidas()
                logTxt.text = "$log\nVocê venceu o inimigo!"
                attack.isEnabled = false
                giveUp.text = "Voltar"
                return@setOnClickListener
            }

            // Inimigo revida
            val danoInimigo: Int = Random.nextInt(6, 13)   // 6 a 12
            vidaJogador -= danoInimigo
            log += "\nO inimigo revidou e causou $danoInimigo de dano."

            if (vidaJogador <= 0) {
                vidaJogador = 0
                log += "\nVocê foi derrotado!"
                attack.isEnabled = false
                giveUp.text = "Voltar"
            }

            atualizarVidas()
            logTxt.text = log
        }
    }


}