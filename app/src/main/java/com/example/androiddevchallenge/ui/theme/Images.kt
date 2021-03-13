package com.example.androiddevchallenge.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class Images(@DrawableRes val welcome: Int, @DrawableRes val logo: Int, @DrawableRes val login: Int)

internal val LocalImages = staticCompositionLocalOf<Images> {
    error("No LocalImages specified")
}
