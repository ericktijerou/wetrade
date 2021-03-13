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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.green
import com.example.androiddevchallenge.ui.theme.red
import com.example.androiddevchallenge.util.DataHelper
import com.example.androiddevchallenge.util.PositionItem
import com.example.androiddevchallenge.util.backHandler
import com.example.androiddevchallenge.util.lerp
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import dev.chrisbanes.accompanist.insets.toPaddingValues
import kotlinx.coroutines.launch

private val HeaderHeight = 70.dp

@ExperimentalMaterialApi
@Composable
fun HomeScroll() {
    BoxWithConstraints {
        val sheetState = rememberSwipeableState(SheetState.Closed)
        val headerSize = with(LocalDensity.current) { HeaderHeight.toPx() }
        val dragRange = constraints.maxHeight - headerSize
        val scope = rememberCoroutineScope()

        backHandler(
            enabled = sheetState.currentValue == SheetState.Open,
            onBack = {
                scope.launch {
                    sheetState.animateTo(SheetState.Closed)
                }
            }
        )

        Box(
            Modifier.swipeable(
                state = sheetState,
                anchors = mapOf(
                    0f to SheetState.Closed,
                    -dragRange to SheetState.Open
                ),
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Vertical
            )
        ) {
            val openFraction = if (sheetState.offset.value.isNaN()) {
                0f
            } else {
                -sheetState.offset.value / dragRange
            }.coerceIn(0f, 1f)
            PositionsSheet(
                openFraction,
                this@BoxWithConstraints.constraints.maxHeight.toFloat()
            ) { state ->
                scope.launch {
                    sheetState.animateTo(state)
                }
            }
        }
    }
}

@Composable
private fun PositionsSheet(
    openFraction: Float,
    height: Float,
    updateSheet: (SheetState) -> Unit
) {
    val headerSize = with(LocalDensity.current) { HeaderHeight.toPx() }
    val headerSheetHeight = headerSize + LocalWindowInsets.current.systemBars.bottom
    val offsetY = lerp(height - headerSheetHeight, 0f, openFraction)
    Surface(
        modifier = Modifier.graphicsLayer {
            translationY = offsetY
        }
    ) {
        PositionItem(openFraction, updateSheet)
    }
}

@Composable
private fun PositionItem(
    openFraction: Float,
    updateSheet: (SheetState) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val itemAlpha = lerp(0f, 1f, 0.2f, 0.8f, openFraction)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = itemAlpha }
                .statusBarsPadding()
        ) {
            val scroll = rememberLazyListState()
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier
                    .clickable { updateSheet(SheetState.Closed) }
                    .height(64.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_positions),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MyTheme.colors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(
                state = scroll,
                contentPadding = LocalWindowInsets.current.systemBars.toPaddingValues(
                    top = false
                )
            ) {
                items(DataHelper.listPositions) { item ->
                    Divider()
                    Item(item)
                }
            }
        }

        val headerAlpha = lerp(1f, 0f, 0f, 0.15f, openFraction)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(HeaderHeight)
                .graphicsLayer { alpha = headerAlpha }
                .clickable { updateSheet(SheetState.Open) }
        ) {
            Text(
                text = stringResource(R.string.label_positions),
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MyTheme.colors.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 40.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun Item(positionItem: PositionItem) {
    Row(
        modifier = Modifier
            .clickable(onClick = { /* todo */ })
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = positionItem.value, style = MyTheme.typography.body1)
            Text(
                text = "${positionItem.percentage}%",
                color = if (positionItem.percentage < 0) red else green,
                style = MyTheme.typography.body1
            )
        }
        Column(
            modifier = Modifier
                .weight(3f)
                .padding(start = 16.dp)
        ) {
            Text(text = positionItem.title, style = MyTheme.typography.h3)
            Text(text = positionItem.subtitle, style = MyTheme.typography.body1)
        }
        Column(modifier = Modifier.weight(2f), horizontalAlignment = Alignment.End) {
            Image(painter = painterResource(id = positionItem.image), contentDescription = "")
        }
    }
}

private enum class SheetState { Open, Closed }
