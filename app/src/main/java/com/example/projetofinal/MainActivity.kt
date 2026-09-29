package com.example.projetofinal

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }
    override fun onSensorChanged(event: SensorEvent?) {
        // Este método é chamado sempre que os dados do sensor mudam
//        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
//            val x = event.values[0] // Aceleração no eixo X
//            val y = event.values[1] // Aceleração no eixo Y
//            val z = event.values[2] // Aceleração no eixo Z
//
//            textViewX.text = "X: %.2f m/s²".format(x)
//            textViewY.text = "Y: %.2f m/s²".format(y)
//            textViewZ.text = "Z: %.2f m/s²".format(z)
//        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Este método é chamado quando a precisão do sensor muda.
        // Para o acelerômetro, geralmente não precisamos fazer nada aqui.
    }
}