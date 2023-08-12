package com.example.strings_e_themes

object Theme {
    var currentTheme = R.style.Theme_StringseThemes

    private const val ACTUAL = R.style.Theme_StringseThemes
    private const val NEW = R.style.MeuTheme

    fun switchTheme() {
        Theme.currentTheme = when (Theme.currentTheme) {
            ACTUAL -> NEW
            NEW -> ACTUAL
            else -> -1
        }
    }
}