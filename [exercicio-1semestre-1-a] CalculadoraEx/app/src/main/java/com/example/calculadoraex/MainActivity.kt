package com.example.calculadoraex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtResultado = findViewById<TextView>(R.id.textView2)
        val btn2Plus = findViewById<Button>(R.id.btn2Plus)

        val btnClear = findViewById<Button>(R.id.btnClear)

        var resultado = 0

        btn2Plus.setOnClickListener {

            resultado += 2
            txtResultado.text = resultado.toString()
        }

        btnClear.setOnClickListener {
            resultado = 0
            txtResultado.text = "0"
        }

    }
}