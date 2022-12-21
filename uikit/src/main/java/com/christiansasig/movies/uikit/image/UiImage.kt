package com.christiansasig.movies.uikit.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.christiansasig.movies.core.R


@Composable
fun UiImage(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .transformations(
                RoundedCornersTransformation(
                    10f
                )
            )
            .build(),
        placeholder = painterResource(
            R.drawable.ic_placeholder_image
        ),
        error = painterResource(
            R.drawable.ic_placeholder_image
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}