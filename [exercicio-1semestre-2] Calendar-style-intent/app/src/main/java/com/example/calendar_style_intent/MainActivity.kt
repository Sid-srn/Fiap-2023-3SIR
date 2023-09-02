package com.example.calendar_style_intent

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.calendar_style_intent.Constants.CALENDAR_DATE
import com.example.calendar_style_intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        databind.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "${dayOfMonth}/${month}/${year}"

            val intent = Intent(this, CreateSchedule::class.java)
            intent.putExtra(CALENDAR_DATE, date)
            //startActivity(intent)
            register.launch(intent)
        }

        databind.btnChangeTheme.setOnClickListener {
            Constants.switchTheme()
            recreate()
        }
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val itemExtra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    data.getParcelableExtra(Constants.DATA_SCHEDULLE, Schedule::class.java)
                } else {
                    data.getParcelableExtra<Schedule>(Constants.DATA_SCHEDULLE)
                }
                itemExtra?.let {
                    databind.imageView.visibility = View.VISIBLE
                    databind.txtSchedule.visibility = View.VISIBLE
                    databind.txtSchedule.text="${it.date} \n ${it.message}"
                }?: run {
                    databind.imageView.visibility = View.GONE
                    databind.txtSchedule.visibility = View.GONE
                }


            }
        }
    }
}