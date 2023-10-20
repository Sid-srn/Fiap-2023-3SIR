package br.com.fiap.ex3rickmorty.model

data class RickMortyModel(
    val info: InfoModel,
    val results: List<ResultModel>
)