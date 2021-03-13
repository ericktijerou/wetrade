package com.example.androiddevchallenge.ui.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen() {
    Text(
        text = "Login",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}