package com.example.intents_motivation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Phrase(
    val phrase: String,
    val type: PhraseType
) : Parcelable
