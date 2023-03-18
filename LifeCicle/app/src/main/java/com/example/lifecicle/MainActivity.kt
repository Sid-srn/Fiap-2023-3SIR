package com.example.lifecicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

const val TAG = "Activity State"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: Lifecycle state is ${this.lifecycle.currentState}")

        val edtxtNome = findViewById<EditText>(R.id.edTxtNome)
        val btnHello = findViewById<Button>(R.id.btnOla)
        val resultado = findViewById<TextView>(R.id.txtOla)

        btnHello.setOnClickListener {
            if (!edtxtNome.text.isNullOrEmpty()) {
                resultado.text = "Olá ${edtxtNome.text}"
            }else{
                edtxtNome.error = "Informe o nome"
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Lifecycle state is ${this.lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: Lifecycle state is ${this.lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: Lifecycle state is ${this.lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: Lifecycle state is ${this.lifecycle.currentState}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart: Lifecycle state is ${this.lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Lifecycle state is ${this.lifecycle.currentState}")
    }
}