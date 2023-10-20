package br.com.fiap.ex3rickmorty.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationModel(
    val name: String,
    val url: String
) : Parcelable