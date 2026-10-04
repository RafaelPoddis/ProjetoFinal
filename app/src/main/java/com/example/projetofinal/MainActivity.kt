package com.example.projetofinal

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

data class Arma(val nome: String, val dano: Int)
class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private lateinit var selecionadoTxt: TextView

    private lateinit var textViewX: TextView
    private lateinit var textViewY: TextView
    private lateinit var textViewZ: TextView

    private var ultimoMovimento = 0L
    private val limiteAgitacao = 20f

    private val armas: List<Arma> = listOf(
        Arma("Adaga", 10),
        Arma("Espada", 12),
        Arma("Machado", 18)
    )
    private var armaAtual: Arma? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (acelerometro == null) {
            Toast.makeText(this, "Acelerômetro não disponível neste dispositivo.", Toast.LENGTH_LONG).show()
        }

        selecionadoTxt = findViewById(R.id.selecionadoTxt)

        val swordBtn = findViewById<ImageButton>(R.id.swordBtn)
        swordBtn.setOnClickListener {
            sortearArma()
            swordBtn.isEnabled = false
            swordBtn.alpha = 0.4f
        }


    }

    private fun sortearArma() {
        val sorteada: Arma = armas.random()
        armaAtual = sorteada
        selecionadoTxt.text = "Arma sorteada: ${sorteada.nome} (dano ${sorteada.dano})"
    }


    override fun onResume() {
        super.onResume()
        acelerometro?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Este método é chamado sempre que os dados do sensor mudam
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val aceleracao = kotlin.math.sqrt(
                x * x + y * y + z * z
            )

            val agora = System.currentTimeMillis()

            if (aceleracao > limiteAgitacao &&
                agora - ultimoMovimento > 1000
            ) {
                ultimoMovimento = agora
                val arma: Arma? = armaAtual

                if(arma == null){
                    Toast.makeText(this, "Sorteie a arma primeiro!", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, ArenaActivity::class.java)
                    intent.putExtra("armaNome", arma.nome)
                    intent.putExtra("armaDano", arma.dano)
                    startActivity(intent)
                }

            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Este método é chamado quando a precisão do sensor muda.
        // Para o acelerômetro, geralmente não precisamos fazer nada aqui.
    }
}