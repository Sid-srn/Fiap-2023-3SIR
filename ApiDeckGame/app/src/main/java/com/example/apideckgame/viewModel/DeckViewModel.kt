package com.example.apideckgame.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apideckgame.model.CardModel
import com.example.apideckgame.model.DeckModel
import com.example.apideckgame.model.IDeckGame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DeckViewModel(
    private val retrofitClient: Retrofit
) : ViewModel() {

    private var deck: DeckModel? = null

    val liveDeck = MutableLiveData<DeckModel>()
    val liveCard = MutableLiveData<CardModel>()
    val liveError = MutableLiveData<String>()

    fun getDeckEndPoint(): IDeckGame =
        retrofitClient.create(IDeckGame::class.java)

    fun getDeck() {
        val endPoint = getDeckEndPoint()
        val callBack = endPoint.getDeck(1)
        callBack.enqueue(object : Callback<DeckModel> {
            override fun onResponse(call: Call<DeckModel>, resp: Response<DeckModel>) {
                deck = resp.body()
                liveDeck.value = deck
            }

            override fun onFailure(call: Call<DeckModel>, t: Throwable) {
                liveError.value = t.message
            }
        })
    }

    fun getCard(cardNumber:Int){
        val endPoint = getDeckEndPoint()
        deck?.let {
            val callBack = endPoint.getDeckCards(it.deck_id, cardNumber)
            callBack.enqueue(object : Callback<CardModel>{
                override fun onResponse(call: Call<CardModel>, response: Response<CardModel>) {
                    liveCard.value = response.body()
                }

                override fun onFailure(call: Call<CardModel>, t: Throwable) {
                    liveError.value = t.message
                }
            })
        }

    }
}