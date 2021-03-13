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
package com.example.androiddevchallenge.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.gray900
import com.example.androiddevchallenge.ui.theme.white600
import com.example.androiddevchallenge.util.ThemedPreview

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(modifier = Modifier.fillMaxWidth())
        Text(
            text = stringResource(R.string.label_balance),
            style = MyTheme.typography.subtitle1,
            color = Color.White,
            modifier = Modifier.paddingFromBaseline(top = 32.dp, bottom = 8.dp)
        )
        Text(
            text = "$73,589.01",
            style = MyTheme.typography.h1,
            color = Color.White,
            modifier = Modifier.paddingFromBaseline(top = 48.dp, bottom = 24.dp)
        )
        Text(
            text = "+412.35 today",
            style = MyTheme.typography.subtitle1,
            color = MyTheme.customColors.custom1,
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 32.dp)
        )
        Button(
            onClick = {},
            shape = CircleShape,
            elevation = ButtonDefaults.elevation(MyTheme.elevations.defaultElevation),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.label_transact),
                style = MyTheme.typography.button,
                color = gray900
            )
        }
        HomeCategoryTabs(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            categories = listOf("Week", "ETFs", "Stocks", "Funds", "Foo1", "Foo2", "Foo3")
        )
    }
}

@Composable
fun Header(modifier: Modifier) {
    Row(modifier) {
        TextButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .paddingFromBaseline(top = 64.dp, bottom = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.label_account),
                style = MyTheme.typography.button,
                color = Color.White
            )
        }
        TextButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .paddingFromBaseline(top = 64.dp, bottom = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.label_watchlist),
                style = MyTheme.typography.button,
                color = white600
            )
        }
        TextButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .paddingFromBaseline(top = 64.dp, bottom = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.label_profile),
                style = MyTheme.typography.button,
                color = white600
            )
        }
    }
}

@Composable
private fun HomeCategoryTabs(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategory: String = "Week"
) {
    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        divider = {},
        backgroundColor = Color.Transparent,
        edgePadding = 12.dp,
        indicator = emptyTabIndicator,
        modifier = modifier
    ) {
        categories.forEachIndexed { index, category ->

                ChoiceChipContent(
                    text = category,
                    selected = index == selectedIndex,
                    modifier = Modifier.padding(4.dp)
                )

        }
    }
}

@Composable
private fun ChoiceChipContent(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .border(
                border = BorderStroke(width = 1.dp, color = Color.White),
                shape = CircleShape
            )
            .height(40.dp),
        color = Color.Transparent
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(end = 4.dp)
            )
            if (selected) {
                Icon(imageVector = Icons.Filled.ExpandMore, contentDescription = "", tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }

    }
}

private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Preview("Main screen")
@Composable
fun PreviewMainScreen() {
    ThemedPreview {
        MainScreen()
    }
}

@Preview("Main screen dark")
@Composable
fun PreviewMainScreenDark() {
    ThemedPreview(darkTheme = true) {
        MainScreen()
    }
}

