package com.example.apideckgame.model

import com.example.apideckgame.model.CardModel
import com.example.apideckgame.model.DeckModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDeckGame {

    @GET("new/shuffle")
    fun getDeck(
        @Query("deck_count") deckCount: Int
    ): Call<DeckModel>

    @GET("{deck_id}/draw")
    fun getDeckCards(
        @Path("deck_id") deckId:String,
        @Query("count") cont:Int
    ): Call<CardModel>
}