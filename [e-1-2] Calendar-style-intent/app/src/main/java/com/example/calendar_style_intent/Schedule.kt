package com.example.calendar_style_intent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule (
    val date: String,
    val message: String
) : Parcelable