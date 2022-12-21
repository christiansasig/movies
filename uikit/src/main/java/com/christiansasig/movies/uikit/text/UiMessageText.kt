package com.christiansasig.movies.uikit.text

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UiMessageText(
    message: String,
    isError: Boolean,
    paddingValues: PaddingValues = PaddingValues(top = 5.dp, start = 16.dp, end = 16.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        Text(
            text = message,
            color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}