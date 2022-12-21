package com.christiansasig.movies.uikit.alert.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.christiansasig.movies.core.R

@Composable
fun LoadingProgressWidget(
    isLoading: Boolean = true,
) {

    if (isLoading) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(12.dp)),
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader))
                LottieAnimation(
                    isPlaying = true,
                    enableMergePaths = true,
                    composition = composition,
                    iterations = Int.MAX_VALUE,
                )
            }
        }
    }
}