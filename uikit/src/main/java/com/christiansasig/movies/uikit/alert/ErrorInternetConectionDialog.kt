package com.christiansasig.movies.uikit.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.christiansasig.movies.core.R
import com.christiansasig.movies.uikit.button.UiSimpleButton
import com.christiansasig.movies.uikit.text.UiTextHeadingBoldTitle
import com.christiansasig.movies.uikit.text.UiTextRegular

@Composable
fun ErrorInternetConnectionDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (

            errorConnectionTitleRef,
            errorConnectionButtonRef
        ) = createRefs()


        Column(  modifier = Modifier
            .constrainAs(errorConnectionTitleRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UiTextHeadingBoldTitle(
                text = stringResource(
                    id = R.string.general_text_no_internet_connection_title
                ),
                style = MaterialTheme.typography.headlineLarge,
            )

            UiTextRegular(
                modifier = Modifier
                    .padding(top = 40.dp),
                text = stringResource(id = R.string.error_network),
                maxLines = 2,
                style = MaterialTheme.typography.titleLarge,
            )
        }

        UiSimpleButton(
            modifier = Modifier
                .constrainAs(errorConnectionButtonRef) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            onClick = {
                onClose()
            },
            text = stringResource(id = R.string.close)
        )
    }
}