package com.christiansasig.movies.uikit.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.christiansasig.movies.uikit.text.UiTextHeadingSemiBoldSubTitle

@Composable
fun UiTopAppBar(
    action: String? = null,
    showBackButton: Boolean = true,
    colorAction: Color = MaterialTheme.colorScheme.onBackground,
    icon: ImageVector = Icons.Filled.ArrowBack,
    actionListener: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 16.dp, bottom = 16.dp)
    ) {
        val (backButton, actionTopAppBar) = createRefs()

        if (showBackButton) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .height(20.dp)
                    .width(20.dp)
            ) {
                Icon(
                    icon,
                    contentDescription = "backButton",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        action?.let { actionText ->
            UiTextHeadingSemiBoldSubTitle(
                modifier = Modifier
                    .constrainAs(actionTopAppBar) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .clickable(
                        onClick = actionListener
                    )
                    .padding(end = 30.dp),
                text = actionText,
                color = colorAction
            )
        }
    }
}