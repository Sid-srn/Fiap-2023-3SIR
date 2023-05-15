package com.example.profilecustonpkmcard

object Constants {
    const val PROFILE_MAIN = "PROFILE_MAIN"
    const val PROFILE_SKILL = "PROFILE_SKILL"

    var currentTheme = R.style.Theme_ProfileCustonPkmCard
    private const val ACTUAL = R.style.Theme_ProfileCustonPkmCard
    private const val CUSTOM = R.style.violet

    fun switchTheme() {
        currentTheme = when (currentTheme) {
            ACTUAL -> CUSTOM
            CUSTOM -> ACTUAL
            else -> -1
        }
    }

    fun getThemeName() = when (currentTheme) {
        ACTUAL -> "Scarlet"
        CUSTOM -> "Violet"
        else -> "Scarlet"
    }
}