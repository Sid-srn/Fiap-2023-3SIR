package br.com.fiap.ex3rickmorty.model

import br.com.fiap.ex3rickmorty.model.RickMortyModel
import retrofit2.Call
import retrofit2.http.GET

interface IRickMortyApi {
    @GET("character")
    fun getCharacters(): Call<RickMortyModel>
}