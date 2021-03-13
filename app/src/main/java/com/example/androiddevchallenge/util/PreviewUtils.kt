package com.example.androiddevchallenge.util

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MyTheme(darkTheme = darkTheme) {
        Surface {
            content()
        }
    }
}
