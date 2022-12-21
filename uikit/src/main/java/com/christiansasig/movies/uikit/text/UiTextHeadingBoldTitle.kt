package com.christiansasig.movies.uikit.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun UiTextHeadingBoldTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    color: Color =  MaterialTheme.colorScheme.onBackground,
    style: TextStyle =  MaterialTheme.typography.titleLarge,
    maxLines: Int = 3,
    text: String,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text ,
        modifier = modifier,
        style = style,
        textAlign = textAlign,
        color = color,
        maxLines = maxLines,
        overflow = overflow
    )
}