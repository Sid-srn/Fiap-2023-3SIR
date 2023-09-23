package com.example.apideckgame.model

data class CardModel(
    val cards: List<Card>,
    val deck_id: String,
    val remaining: Int,
    val success: Boolean
)