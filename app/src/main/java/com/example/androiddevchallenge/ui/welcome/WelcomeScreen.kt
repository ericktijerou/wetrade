package com.example.androiddevchallenge.ui.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.gray900
import com.example.androiddevchallenge.util.ThemedPreview
import java.util.*

@Composable
fun WelcomeScreen(goToLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyTheme.colors.background), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = MyTheme.images.welcome),
            contentDescription = "welcome",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Icon(painterResource(id = MyTheme.images.logo), contentDescription = "logo", tint = Color.White)
        Row(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)) {
            Button(
                onClick = goToLogin,
                shape = CircleShape,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_start),
                    style = MyTheme.typography.button,
                    color = gray900
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                onClick = goToLogin,
                shape = CircleShape,
                border = BorderStroke(1.dp, Color.Yellow),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = outlinedButtonColors(backgroundColor = Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.label_login),
                    style = MyTheme.typography.button,
                    color = Color.Yellow
                )
            }
        }
    }
}

@Preview("Welcome screen")
@Composable
fun PreviewWelcomeScreen() {
    ThemedPreview {
        WelcomeScreen(goToLogin = {})
    }
}

@Preview("Welcome screen dark")
@Composable
fun PreviewWelcomeScreenDark() {
    ThemedPreview(darkTheme = true) {
        WelcomeScreen(goToLogin = {})
    }
}
