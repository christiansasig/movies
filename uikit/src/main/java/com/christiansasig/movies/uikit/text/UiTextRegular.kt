package com.christiansasig.movies.uikit.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun UiTextRegular(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onBackground,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    maxLines: Int = Int.MAX_VALUE,
    text: String? = null,
) {
    Text(
        text = text ?: "",
        modifier = modifier,
        style = style,
        textAlign = textAlign,
        color = color,
        maxLines = maxLines
    )
}