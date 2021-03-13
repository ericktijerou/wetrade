package com.example.androiddevchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun UserInputText(
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    hint: String,
    imageVector: ImageVector
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .height(56.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .border(
                    border = BorderStroke(width = 1.dp, color = MyTheme.colors.onSurface),
                    shape = MyTheme.shapes.small
                )
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MyTheme.customColors.loginBackground)
                    .padding(start = 16.dp, end = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                val (icon, textField, text) = createRefs()
                Icon(
                    imageVector = imageVector,
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .constrainAs(icon) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                )
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { onTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = ImeAction.Send
                    ),
                    maxLines = 1,
                    cursorBrush = SolidColor(LocalContentColor.current),
                    textStyle = MaterialTheme.typography.body1.copy(color = MyTheme.colors.onSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(textField) {
                            start.linkTo(icon.end, margin = 8.dp)
                            end.linkTo(parent.end)
                            linkTo(top = parent.top, bottom = parent.bottom)
                            width = Dimension.fillToConstraints
                        }
                )

                if (textFieldValue.text.isEmpty()) {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.body1.copy(color = MyTheme.colors.onSurface),
                        modifier = Modifier.constrainAs(text) {
                            start.linkTo(textField.start)
                            linkTo(top = parent.top, bottom = parent.bottom)
                        },
                    )
                }
            }
        }
    }
}