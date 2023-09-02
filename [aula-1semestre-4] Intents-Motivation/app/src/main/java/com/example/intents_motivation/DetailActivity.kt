package com.example.intents_motivation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.intents_motivation.databinding.ActivityDetailBinding
import com.example.intents_motivation.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var databind: ActivityDetailBinding
    private lateinit var phrase: Phrase

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(databind.root)
        val type = intent.getIntExtra("PHRASE_TYPE", -1)
        val enumPhrase = PhraseType.values().first { it.ordinal == type }
        phrase = PhraseRepository().getPhrase(enumPhrase)

        databind.imgBtnPhraseType.setImageResource(getImgType(phrase.type))
        databind.textView2.text = phrase.phrase

        databind.btnClosePhrase.setOnClickListener {
            Intent().apply {
                putExtra("PHRASE_ITEM", phrase)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }

    private fun getImgType(type:PhraseType) =
        when(type){
            PhraseType.ALL -> R.drawable.ic_data_object_24
            PhraseType.SUNNY -> R.drawable.ic_sunny_24
            PhraseType.HAPPY -> R.drawable.ic_emoji_emotions_24
        }

    override fun onBackPressed() {
        Intent().apply {
            putExtra("PHRASE_ITEM", phrase)
            setResult(RESULT_OK, this)
        }
        this.finish()
    }


}