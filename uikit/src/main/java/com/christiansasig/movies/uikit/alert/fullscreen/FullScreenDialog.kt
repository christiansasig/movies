package com.christiansasig.movies.uikit.alert.fullscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.christiansasig.movies.uikit.alert.content.FContentDialog
import com.christiansasig.movies.uikit.alert.model.UiDialogModel

@Composable
fun FullScreenDialog(
    dialog: UiDialogModel,
    onClickButton: () -> Unit = {},
    onSecondClickButton: () -> Unit = {},
    trustedContactListener: () -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape,
        color = MaterialTheme.colorScheme.background
    ) {
        FContentDialog(
            dialog = dialog,
            onClickButton = onClickButton,
            onClickSecondButton = onSecondClickButton,
            trustedContactListener = trustedContactListener
        )
    }
}