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
package com.example.androiddevchallenge.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.component.UserInputText
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.gray900
import com.example.androiddevchallenge.util.ThemedPreview

@Composable
fun LoginScreen(goToMain: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyTheme.colors.surface),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column {
            Image(
                painter = painterResource(id = MyTheme.images.login),
                contentDescription = "welcome",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            var textState by remember { mutableStateOf(TextFieldValue()) }
            UserInputText(
                textFieldValue = textState,
                onTextChanged = { textState = it },
                imageVector = Icons.Filled.MailOutline,
                hint = stringResource(R.string.hint_email),
                modifier = Modifier.padding(top = 40.dp)
            )
            UserInputText(
                textFieldValue = textState,
                onTextChanged = { textState = it },
                imageVector = Icons.Filled.Password,
                hint = stringResource(R.string.hint_password),
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = goToMain,
                shape = CircleShape,
                elevation = elevation(MyTheme.elevations.defaultElevation),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_login),
                    style = MyTheme.typography.button,
                    color = gray900
                )
            }
        }
        Text(
            text = "Welcome\nback",
            style = MyTheme.typography.h2, color = Color.White,
            modifier = Modifier.paddingFromBaseline(top = 152.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview("Login screen")
@Composable
fun PreviewLoginScreen() {
    ThemedPreview {
        LoginScreen(goToMain = {})
    }
}

@Preview("Login screen dark")
@Composable
fun PreviewLoginScreenDark() {
    ThemedPreview(darkTheme = true) {
        LoginScreen(goToMain = {})
    }
}
