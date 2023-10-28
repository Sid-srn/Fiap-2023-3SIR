package com.example.sharedprefrences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedprefrences.Constantes.LISTA_TAREFAS
import com.example.sharedprefrences.Constantes.TAREFAS_BASE
import com.example.sharedprefrences.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    lateinit var bind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val tarefas = getTarefas()


        updateList(tarefas)

        bind.btnSave.setOnClickListener {
            val priori = Integer.parseInt(bind.txtPrioridade.text.toString())
            val desc = bind.txtDescricao.text.toString()
            tarefas.add(TarefaModel(priori, desc))
            saveList(tarefas)
            updateList(tarefas)
        }

    }


    //viewModel
    fun saveList(listTarefas:List<TarefaModel>){
        val listJson = Gson().toJson(listTarefas)

        val sharedPreferences = getSharedPreferences(TAREFAS_BASE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Constantes.LISTA_TAREFAS, listJson)
        editor.apply()
    }

    //viewModel
    fun getTarefas() : MutableList<TarefaModel> {
        val shared = getSharedPreferences(TAREFAS_BASE, Context.MODE_PRIVATE)
        val jsonLista = shared.getString(LISTA_TAREFAS, null)
        if (jsonLista == null){
            return mutableListOf()
        }else{
            val type: Type = object : TypeToken<MutableList<TarefaModel>>() {}.type
            val listaTarefas = Gson().fromJson<MutableList<TarefaModel>>(jsonLista, type)
            return listaTarefas
        }
    }

    fun updateList(lista : List<TarefaModel>){
        if(lista.size > 0){
            lista.sortedBy { it.prioridade }
            var value = ""
            for (tarefa : TarefaModel in lista){
                value += " - ${tarefa.prioridade} -> ${tarefa.descricao} \n"
            }
            bind.txtLista.text = value

        }else{
            bind.txtLista.text = "nada para fazer"
        }
    }
}