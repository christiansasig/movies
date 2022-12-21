package com.christiansasig.movies.uikit.divider

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christiansasig.movies.uikit.style.GreyFiado1

@Composable
fun UiDivider(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(
        top = 10.dp,
        bottom = 10.dp
    )
) {
    Divider(
        modifier = modifier.padding(paddingValues),
        color = GreyFiado1,
        thickness = 1.dp,
    )
}