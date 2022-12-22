package com.christiansasig.movies.uikit.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.christiansasig.movies.core.strings.toUrlImage
import com.christiansasig.movies.uikit.image.UiImage
import com.christiansasig.movies.uikit.text.UiTextRegular

@Composable
fun UiRowMovie(
    modifier: Modifier = Modifier.fillMaxWidth(),
    color: Color = MaterialTheme.colorScheme.secondary,
    styleTitle: TextStyle = MaterialTheme.typography.titleSmall,
    title: String,
    imageUrl: String?,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
            UiImage(
                modifier = Modifier
                    .fillMaxWidth(),
                imageUrl = imageUrl?.toUrlImage()
            )

            UiTextRegular(
                modifier = Modifier
                    .padding(start = 5.dp),
                color = color,
                style = styleTitle,
                text = title,
                maxLines = 2
            )
    }
}