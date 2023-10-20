package br.com.fiap.ex3rickmorty.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.ex3rickmorty.RetrofitFactory
import retrofit2.Retrofit

class RickMortyViewModelFactory (
    private val retroFitClient: Retrofit = RetrofitFactory.retroFitInstance("https://rickandmortyapi.com/api/")
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(RickMortyViewModel::class.java) ->
                    RickMortyViewModel(retroFitClient)
                else ->
                    throw IllegalArgumentException("Classe desconhecida.")
            }
        } as T
    }
}