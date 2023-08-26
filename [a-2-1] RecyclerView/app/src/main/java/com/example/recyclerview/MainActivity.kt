package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IDialogItem {

    private lateinit var bind: ActivityMainBinding
    private val adapter = ItemListaAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.itensRecycler.layoutManager = LinearLayoutManager(this)
        bind.itensRecycler.adapter = adapter
        adapter.setList(
            mutableListOf(
                ItemModel(
                    "Item A",
                    "Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe Detalhe ",
                    false
                ),
                ItemModel(
                    "Outro Item",
                    "Outro Outro Outro Outro Outro Outro Outro Outro Outro Outro Outro Outro ",
                    false
                ),
                ItemModel(
                    "Terceiro item",
                    "Terceiro Terceiro Terceiro Terceiro Terceiro Terceiro Terceiro Terceiro ",
                    false
                )
            )
        )

        bind.floatBtn.setOnClickListener {
            PopupItemDialog.buildDialog(this).show(supportFragmentManager, "ADDDIALOG")
        }
    }

    override fun addItem(item: ItemModel) {
        adapter.addList(item)
    }

    override fun openEditItem(posicao: Int, item: ItemModel) {
        PopupItemDialog.buildDialog(this, posicao, item).show(supportFragmentManager, "EDITIALOG")
    }

    override fun editItem(posicao: Int, item: ItemModel) {
        adapter.editItem(posicao = posicao, item = item)
    }

    override fun removeItem(item: ItemModel) {
        val confirmDialog = AlertDialog.Builder(this)
        confirmDialog.setTitle("Exclusao")
        confirmDialog.setMessage("confirma a excluso do ${item.item}")
        confirmDialog.setPositiveButton("sim"){dialog,_->
            adapter.removeItem(item)
            dialog.cancel()
        }
        confirmDialog.setNegativeButton("não"){dialog,_ ->
            dialog.cancel()
        }
        confirmDialog.show()
    }
}