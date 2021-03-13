package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.NavGraph
import com.example.androiddevchallenge.util.Screen
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun MyApp() {
    ProvideWindowInsets {
        MyTheme {
            NavGraph(startDestination = Screen.Welcome)
        }
    }
}
