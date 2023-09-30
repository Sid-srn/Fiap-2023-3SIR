package com.example.apideckgame.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apideckgame.NetworkUtils
import retrofit2.Retrofit
import java.lang.IllegalStateException

class DeckViewModelFactory(
    private val retrofit: Retrofit = NetworkUtils.getRetrofitInstance("https://deckofcardsapi.com/api/deck/")
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass){
            when{
                isAssignableFrom(DeckViewModel::class.java) ->
                    DeckViewModel(retrofit)
                else ->
                    throw  IllegalStateException("classe desconhecida")
            }
        } as T
    }
}