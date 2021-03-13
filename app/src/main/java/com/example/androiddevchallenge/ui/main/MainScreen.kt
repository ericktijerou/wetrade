package com.example.androiddevchallenge.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    Text(
        text = "Main",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}