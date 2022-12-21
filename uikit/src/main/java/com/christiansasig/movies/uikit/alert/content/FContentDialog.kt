package com.christiansasig.movies.uikit.alert.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.christiansasig.movies.uikit.alert.model.UiDialogContentModel
import com.christiansasig.movies.uikit.alert.model.UiDialogModel
import com.christiansasig.movies.uikit.button.UiSimpleButton
import com.christiansasig.movies.uikit.button.UiTextButton
import com.christiansasig.movies.uikit.style.bodyRegularM

@Composable
fun FContentDialog(
    dialog: UiDialogModel,
    lottieIterations: Int = 1,
    onClickButton: () -> Unit,
    onClickSecondButton: () -> Unit = {},
    trustedContactListener: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(bottom = 30.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        val (content, button) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(button.top)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (dialog.resourceType) {
                UiDialogModel.FDialogResourceType.IMAGE -> {
                    Image(
                        painter = painterResource(id = dialog.content.resourceId),
                        contentDescription = "Launcher",
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                UiDialogModel.FDialogResourceType.LOTTIE -> {

                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = dialog.content.titleId,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 8
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (dialog.content.subTitleId.isNotEmpty()) {
                Text(
                    text = dialog.content.subTitleId,
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    style = bodyRegularM,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 6
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            dialog.content.trustedContact?.let { trustedContact ->
                Text(
                    text = trustedContact,
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 9.dp, bottom = 45.dp)
                        .fillMaxWidth()
                        .clickable { trustedContactListener.invoke() },
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color =  MaterialTheme.colorScheme.onBackground,
                    maxLines = 6
                )
            }

        }

        if (dialog.hasButtons()) {
            when (dialog.quantityButtons) {
                1 -> {
                    UiSimpleButton(
                        onClick = onClickButton,
                        text = dialog.content.buttonTitleId[0].uppercase(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 50.dp, top = 20.dp)
                            .constrainAs(button) {
                                top.linkTo(content.bottom)
                                bottom.linkTo(parent.bottom, margin = 20.dp)
                            })
                }

                2 -> {
                    ConstraintLayout(
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .constrainAs(button) {
                                top.linkTo(content.bottom)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        val (firstButton, secondButton) = createRefs()
                        when (dialog.content.buttonType[0]) {
                            UiDialogContentModel.FDialogButtonType.TEXT_BUTTON ->
                                UiTextButton(
                                    onClick = onClickButton,
                                    text = dialog.content.buttonTitleId[0].uppercase(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp,
                                            top = 20.dp
                                        )
                                        .constrainAs(firstButton) {
                                            top.linkTo(parent.top)
                                        })

                            UiDialogContentModel.FDialogButtonType.BUTTON ->
                                UiSimpleButton(
                                    onClick = onClickButton,
                                    text = dialog.content.buttonTitleId[0].uppercase(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp,
                                            top = 20.dp
                                        )
                                        .constrainAs(firstButton) {
                                            top.linkTo(parent.top)
                                        })
                        }
                        when (dialog.content.buttonType[1]) {
                            UiDialogContentModel.FDialogButtonType.TEXT_BUTTON ->
                                UiTextButton(
                                    onClick = onClickSecondButton,
                                    text = dialog.content.buttonTitleId[1].uppercase(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp, bottom = 60.dp)
                                        .constrainAs(secondButton) {
                                            top.linkTo(firstButton.bottom)
                                        })

                            UiDialogContentModel.FDialogButtonType.BUTTON ->
                                UiSimpleButton(
                                    onClick = onClickSecondButton,
                                    text = dialog.content.buttonTitleId[1].uppercase(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp, bottom = 60.dp)
                                        .constrainAs(secondButton) {
                                            top.linkTo(firstButton.bottom)
                                        })
                        }
                    }
                }
            }
        }
    }
}


