package com.christiansasig.movies.uikit.alert.model

import androidx.compose.ui.graphics.Color
import com.christiansasig.movies.core.R

data class UiDialogModel(
    val content: UiDialogContentModel,
    val type: FDialogType = FDialogType.FULL_SCREEN,
    val resourceType: FDialogResourceType = FDialogResourceType.IMAGE,
    val quantityButtons: Int = 0,
    val isOnDismissActive: Boolean = false,
    val dismissOnBackPress: Boolean = true,
    val showButtonClose: Boolean = true,
    val dismissOnClickOutside: Boolean = true,
) {

    enum class FDialogType {
        FULL_SCREEN, MIDDLE, UP, BOTTOM
    }

    enum class FDialogResourceType {
        IMAGE, LOTTIE
    }

    fun hasButtons(): Boolean {
        return quantityButtons != 0
    }

}

data class UiDialogContentModel(
    val resourceId: Int = 0,
    val titleId: String = "",
    var subTitleId: String = "",
    var trustedContact: String? = null,
    val buttonTitleId: List<String> = listOf(),
    val timeToDismiss: Long = 0,
    val buttonType: List<FDialogButtonType> = listOf(
        FDialogButtonType.BUTTON,
        FDialogButtonType.BUTTON
    ),
    val buttonColor: List<Color> = listOf()
) {
    companion object {
        val genericErrorDialog: UiDialogContentModel = UiDialogContentModel(
            resourceId = R.drawable.ic_alert,
            titleId = "",
            subTitleId = "",
            buttonTitleId = listOf(""),
        )
    }

    enum class FDialogButtonType {
        BUTTON, TEXT_BUTTON
    }
}
