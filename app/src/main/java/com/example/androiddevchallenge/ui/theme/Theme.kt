/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.util.LocalSysUiController

private val DarkColorPalette = darkColors(
    primary = yellow,
    onPrimary = gray900,
    background = gray900,
    surface = gray700,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = yellow,
    onPrimary = gray900,
    background = purple,
    surface = Color.White,
    onBackground = Color.White,
    onSurface = gray900
)

private val LightCustomColorPalette = MyThemeColors(
    custom1 = green,
    custom2 = red,
    loginBackground = Color.White,
    isDark = false
)

private val DarkCustomColorPalette = MyThemeColors(
    custom1 = green,
    custom2 = red,
    loginBackground = gray900,
    isDark = true
)

private val LightElevation = Elevations(defaultElevation = 0.dp)

private val DarkElevation = Elevations(defaultElevation = 0.dp)

private val LightImages = Images(
    welcome = R.drawable.ic_welcome_bg,
    logo = R.drawable.ic_logo,
    login = R.drawable.ic_login_bg
)

private val DarkImages = Images(
    welcome = R.drawable.ic_welcome_bg,
    logo = R.drawable.ic_logo,
    login = R.drawable.ic_login_bg
)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val (colors, customColors) = if (darkTheme) DarkColorPalette to DarkCustomColorPalette else LightColorPalette to LightCustomColorPalette
    val sysUiController = LocalSysUiController.current
    val elevation = if (darkTheme) DarkElevation else LightElevation
    val images = if (darkTheme) DarkImages else LightImages
    SideEffect {
        sysUiController.setStatusBarColor(color = colors.background, darkIcons = false)
        sysUiController.setNavigationBarColor(color = colors.background)
    }
    ProvideMyThemeColors(elevation, images, customColors) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = Shapes,
            content = content
        )
    }
}

object MyTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val elevations: Elevations
        @Composable
        get() = LocalElevations.current

    val images: Images
        @Composable
        get() = LocalImages.current

    val customColors: MyThemeColors
        @Composable
        get() = LocalMyThemeColors.current
}

@Composable
fun ProvideMyThemeColors(
    elevation: Elevations,
    images: Images,
    colors: MyThemeColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(
        LocalElevations provides elevation,
        LocalImages provides images,
        LocalMyThemeColors provides colorPalette,
        content = content
    )
}

@Stable
class MyThemeColors(
    custom1: Color,
    custom2: Color,
    loginBackground: Color,
    isDark: Boolean
) {
    var custom1 by mutableStateOf(custom1)
        private set
    var custom2 by mutableStateOf(custom2)
        private set
    var loginBackground by mutableStateOf(loginBackground)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: MyThemeColors) {
        custom1 = other.custom1
        custom2 = other.custom2
        loginBackground = other.loginBackground
        isDark = other.isDark
    }
}

private val LocalMyThemeColors = staticCompositionLocalOf<MyThemeColors> {
    error("No JetsnackColorPalette provided")
}
