package com.example.strings_e_themes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Theme.currentTheme)
        setContentView(R.layout.activity_main)

        val txtLyric2 = findViewById<TextView>(R.id.secondLyric)

        txtLyric2.text = getText(R.string.second_lyric_content)

        val btnChange = findViewById<Button>(R.id.normal_button)

        btnChange.setOnClickListener {
            Theme.switchTheme()
            recreate()
        }

        val switch = findViewById<Switch>(R.id.switchButton)

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
    }
}