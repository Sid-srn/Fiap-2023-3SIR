package com.example.recyclerview

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private val adapter = ItemListaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.itensRecycler.layoutManager = LinearLayoutManager(this)
        bind.itensRecycler.adapter = adapter
        adapter.setList(mutableListOf(
            ItemModel("Item A"
                , "Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe "
                , false),
            ItemModel("Outro Item"
                , "Outro Outro Outro Outro Outro Outro Outro Outro Outro Outro Outro Outro "
                , false),
            ItemModel("Terceiro item"
                , "Terceiro Terceiro Terceiro Terceiro Terceiro Terceiro Terceiro Terceiro "
                , false)
        ))
    }
}