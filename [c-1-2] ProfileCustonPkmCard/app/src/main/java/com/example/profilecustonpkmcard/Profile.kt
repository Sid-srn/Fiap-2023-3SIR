package com.example.profilecustonpkmcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val name: String,
    val description: String,
    val skills: MutableList<String>,
) : Parcelable