package com.example.calendar_style_intent

object Constants {
    const val CALENDAR_DATE = "CALENDAR_DATE"
    const val DATA_SCHEDULLE = "DATA_SCHEDULLE"

    var currentTheme = R.style.Theme_Calendarstyleintent
    private const val ACTUAL = R.style.Theme_Calendarstyleintent
    private const val CUSTOM = R.style.CustomTheme

    fun switchTheme() {
        currentTheme = when (currentTheme) {
            ACTUAL -> CUSTOM
            CUSTOM -> ACTUAL
            else -> -1
        }
    }
}