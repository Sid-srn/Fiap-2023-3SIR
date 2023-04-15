package com.example.intents_motivation

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.intents_motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding
    private var phraseType = PhraseType.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        databind.textView.visibility = View.GONE
        databind.icLastPhrase.visibility = View.GONE
        databind.txtLastPhrase.visibility = View.GONE

        databind.imgBtnAll.setOnClickListener {
            phraseType = PhraseType.ALL
            updateType()
        }
        databind.imgBtnSun.setOnClickListener {
            phraseType = PhraseType.SUNNY
            updateType()
        }
        databind.imgButnSmile.setOnClickListener {
            phraseType = PhraseType.HAPPY
            updateType()
        }

        databind.btnOpenPhrase.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("PHRASE_TYPE", phraseType.ordinal)
            //startActivity(intent)
            register.launch(intent)
        }
    }


    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val phraseExtra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    data.getParcelableExtra("PHRASE_ITEM", Phrase::class.java)
                } else {
                    data.getParcelableExtra<Phrase>("PHRASE_ITEM")
                }
                phraseExtra?.let {
                    databind.textView.visibility = View.VISIBLE
                    databind.icLastPhrase.visibility = View.VISIBLE
                    databind.txtLastPhrase.visibility = View.VISIBLE
                    databind.icLastPhrase.setImageResource(getImgType(phraseExtra.type))
                    databind.txtLastPhrase.text = phraseExtra.phrase
                }


            }
        }
    }

    private fun updateType() {
        when (phraseType) {
            PhraseType.ALL -> {
                databind.imgBtnAll.setColorFilter(getColor(R.color.white))
                databind.imgBtnSun.setColorFilter(getColor(R.color.gray))
                databind.imgButnSmile.setColorFilter(getColor(R.color.gray))
            }
            PhraseType.SUNNY -> {
                databind.imgBtnAll.setColorFilter(getColor(R.color.gray))
                databind.imgBtnSun.setColorFilter(getColor(R.color.white))
                databind.imgButnSmile.setColorFilter(getColor(R.color.gray))
            }
            PhraseType.HAPPY -> {
                databind.imgBtnAll.setColorFilter(getColor(R.color.gray))
                databind.imgBtnSun.setColorFilter(getColor(R.color.gray))
                databind.imgButnSmile.setColorFilter(getColor(R.color.white))
            }


        }
    }

    private fun getImgType(type:PhraseType) =
        when(type){
            PhraseType.ALL -> R.drawable.ic_data_object_24
            PhraseType.SUNNY -> R.drawable.ic_sunny_24
            PhraseType.HAPPY -> R.drawable.ic_emoji_emotions_24
        }
}