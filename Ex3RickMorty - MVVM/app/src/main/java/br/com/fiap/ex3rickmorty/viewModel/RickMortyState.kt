package br.com.fiap.ex3rickmorty.viewModel

import br.com.fiap.ex3rickmorty.model.ResultModel

sealed class RickMortyViewState {
    object Loading : RickMortyViewState()
    data class Success(val characters: List<ResultModel>?) : RickMortyViewState()
    data class Error(val errorMessage: String?) : RickMortyViewState()
}