package br.com.fiap.ex3rickmorty.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.ex3rickmorty.model.Contants.ITEM_RM
import br.com.fiap.ex3rickmorty.databinding.ActivityMainBinding
import br.com.fiap.ex3rickmorty.model.IFavoriteRickMorty
import br.com.fiap.ex3rickmorty.model.ResultModel
import br.com.fiap.ex3rickmorty.viewModel.RickMortyViewModel
import br.com.fiap.ex3rickmorty.viewModel.RickMortyViewModelFactory
import br.com.fiap.ex3rickmorty.viewModel.RickMortyViewState

class MainActivity : AppCompatActivity(), IFavoriteRickMorty {

    private lateinit var databind: ActivityMainBinding
    private val adapter = RickMortyAdapter(this)

    private val deckFactory = RickMortyViewModelFactory()
    private lateinit var viewModel: RickMortyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        viewModel = ViewModelProvider(this, deckFactory)[RickMortyViewModel::class.java]

        observeViewModel()

        setupReclycler()
        viewModel.getCharacters()
    }

    private fun observeViewModel(){
        viewModel.rickMortyViewState.observe(this, Observer { state ->
            when (state) {
                is RickMortyViewState.Loading -> {
                    databind.progress.visibility = View.VISIBLE
                }

                is RickMortyViewState.Success -> {
                    state.characters?.let { charaters ->
                        adapter.setList(charaters)
                    }
                    databind.progress.visibility = View.GONE
                }

                is RickMortyViewState.Error -> {
                    showErrorMessage(state.errorMessage)
                }
            }

        })
    }

    private fun setupReclycler() {
        databind.rvRickMortyList.layoutManager = LinearLayoutManager(this)
        databind.rvRickMortyList.adapter = adapter

        adapter.setList(mutableListOf())
    }

    private fun showErrorMessage(message: String?) {
        val erroDialog = AlertDialog.Builder(this)
        erroDialog.setTitle("Erro")
        erroDialog.setMessage(
            "Erro de excução $message"
        )
        erroDialog.setPositiveButton("ok") { dialog, _ ->
            dialog.cancel()
        }
        erroDialog.show()
        databind.progress.visibility = View.GONE
    }

    override fun detailItem(model: ResultModel) {
        val intent = Intent(this, RickMortyDetailActivity::class.java)
        intent.putExtra(ITEM_RM, model)
        startActivity(intent)
    }

}