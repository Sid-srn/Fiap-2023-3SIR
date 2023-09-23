package com.example.apideckgame.model

data class DeckModel(
    val deck_id: String,
    val remaining: Int,
    val shuffled: Boolean,
    val success: Boolean
)