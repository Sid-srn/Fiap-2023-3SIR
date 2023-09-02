package com.example.recyclerpaises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerpaises.databinding.ActivityMainBinding
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity(), IAddPais {
    lateinit var bind: ActivityMainBinding
    val adapter = PaisesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.paisesRecycler.adapter = adapter
        bind.paisesRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapter.setList(mutableListOf(
            PaisModel("Brasil", "America do Sul"),
            PaisModel("Argentina", "America do Sul"),
            PaisModel("China", "Asia"),
            PaisModel("Egito", "Africa"),
            PaisModel("Portugal", "Europa")
        ))

        bind.addPaisBtn.setOnClickListener {
            AddDialogFragment.newInstance(this)
                .show(supportFragmentManager,"ADD_DIALOG")
        }

    }

    override fun addPais(pais: PaisModel) {
        adapter.addPais(pais)
    }
}