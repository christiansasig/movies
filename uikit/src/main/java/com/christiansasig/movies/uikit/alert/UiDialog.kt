package com.christiansasig.movies.uikit.alert

import android.os.CountDownTimer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.christiansasig.movies.uikit.alert.alertdialog.FAlertDialog
import com.christiansasig.movies.uikit.alert.fullscreen.FullScreenDialog
import com.christiansasig.movies.uikit.alert.model.UiDialogModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UiDialog(
    dialog: UiDialogModel,
    onClickButton: () -> Unit = {},
    onClickSecondButton: () -> Unit = {},
    onDismissAction: () -> Unit = {},
    trustedContactListener: () -> Unit = {},
    onCloseAction: () -> Unit = {},
    onCloseButton: () -> Unit = {},
) {
    val showDialog: MutableState<Boolean> = remember { mutableStateOf(true) }

    if (dialog.isOnDismissActive) {
        object : CountDownTimer(dialog.content.timeToDismiss, 1000) {
            override fun onTick(millisRemaining: Long) {}

            override fun onFinish() {
                showDialog.value = false
                onDismissAction()
            }
        }.start()
    }

    if (showDialog.value) {

        val isFullScreen = when (dialog.type) {
            UiDialogModel.FDialogType.FULL_SCREEN -> true
            UiDialogModel.FDialogType.MIDDLE,
            UiDialogModel.FDialogType.BOTTOM,
            UiDialogModel.FDialogType.UP,
            -> false
        }

        Dialog(
            onDismissRequest = {
                showDialog.value = !showDialog.value
                onCloseAction.invoke()
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = !isFullScreen,
                dismissOnBackPress = dialog.dismissOnBackPress,
                dismissOnClickOutside = dialog.dismissOnClickOutside
            )
        ) {
            if (isFullScreen) {
                    FullScreenDialog(
                        dialog = dialog,
                        onClickButton = {
                            showDialog.value = !showDialog.value
                            onClickButton.invoke()
                        },
                        onSecondClickButton = {
                            showDialog.value = !showDialog.value
                            onClickSecondButton.invoke()
                        },
                        trustedContactListener = trustedContactListener
                    )
            } else {
                FAlertDialog(
                    dialog = dialog,
                    onClickButton =
                    {
                        showDialog.value = !showDialog.value
                        onClickButton.invoke()
                    },
                    onClickSecondButton = onClickSecondButton,
                    trustedContactListener = trustedContactListener,
                    onCloseButton = onCloseButton
                )
            }
        }
    }
}
