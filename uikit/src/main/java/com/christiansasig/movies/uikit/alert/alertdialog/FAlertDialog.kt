package com.christiansasig.movies.uikit.alert.alertdialog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.christiansasig.movies.uikit.alert.content.FAlertContentDialog
import com.christiansasig.movies.uikit.alert.model.UiDialogModel

@Composable
fun FAlertDialog(
    dialog: UiDialogModel,
    onCloseButton: () -> Unit,
    onClickButton: () -> Unit = {},
    onClickSecondButton: () -> Unit = {},
    trustedContactListener: () -> Unit = {}
) {
    when (dialog.type) {
        UiDialogModel.FDialogType.UP -> {
            Row(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    verticalAlignment = Alignment.Top
            ) {
                FAlertContentDialog(
                        dialog = dialog,
                        onClickButton = onClickButton,
                        onClickSecondButton = onClickSecondButton,
                        trustedContactListener = trustedContactListener,
                        onCloseButton = onCloseButton
                )
            }
        }
        UiDialogModel.FDialogType.BOTTOM -> {
            Row(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
            ) {
                FAlertContentDialog(
                        dialog = dialog,
                        onClickButton = onClickButton,
                        onClickSecondButton = onClickSecondButton,
                        trustedContactListener = trustedContactListener,
                        onCloseButton = onCloseButton
                )
            }
        }
        UiDialogModel.FDialogType.MIDDLE -> {
            FAlertContentDialog(
                    dialog = dialog,
                    onClickButton = onClickButton,
                    onClickSecondButton = onClickSecondButton,
                    trustedContactListener = trustedContactListener,
                    onCloseButton = onCloseButton
            )
        }
        else -> {
            FAlertContentDialog(
                    dialog = dialog,
                    onClickButton = onClickButton,
                    onClickSecondButton = onClickSecondButton,
                    trustedContactListener = trustedContactListener,
                    onCloseButton = onCloseButton
            )
        }
    }
}
