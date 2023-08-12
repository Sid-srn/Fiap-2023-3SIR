package com.example.calendar_style_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendar_style_intent.Constants.CALENDAR_DATE
import com.example.calendar_style_intent.Constants.DATA_SCHEDULLE
import com.example.calendar_style_intent.databinding.ActivityCreateScheduleBinding
import com.example.calendar_style_intent.databinding.ActivityMainBinding

class CreateSchedule : AppCompatActivity() {

    private lateinit var databind: ActivityCreateScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)
        databind = ActivityCreateScheduleBinding.inflate(layoutInflater)
        setContentView(databind.root)
        val dataValue = intent.getStringExtra(CALENDAR_DATE)?:""

        databind.txtDate.text = "** ${dataValue} **"

        databind.btnSave.setOnClickListener {
            Intent().apply {
                putExtra(DATA_SCHEDULLE, Schedule(dataValue, databind.txtScheduleText.text.toString()))
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }
}