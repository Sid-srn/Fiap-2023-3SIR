package br.com.fiap.ex3rickmorty.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultModel(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationModel,
    val name: String,
    val origin: OriginModel,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    var favorite: Boolean = false
) : Parcelable