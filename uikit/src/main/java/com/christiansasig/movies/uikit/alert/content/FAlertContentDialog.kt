package com.christiansasig.movies.uikit.alert.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.christiansasig.movies.uikit.alert.model.UiDialogContentModel
import com.christiansasig.movies.uikit.alert.model.UiDialogModel
import com.christiansasig.movies.uikit.button.UiSimpleButton
import com.christiansasig.movies.uikit.button.UiTextButton
import com.christiansasig.movies.uikit.iconbutton.UiIconButton
import com.christiansasig.movies.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAlertContentDialog(
    dialog: UiDialogModel,
    onCloseButton: () -> Unit,
    onClickButton: () -> Unit,
    onClickSecondButton: () -> Unit = {},
    trustedContactListener: (() -> Unit)? = null,
    lottieIterations: Int = 1
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column{
            if(dialog.showButtonClose){
                UiIconButton(
                    modifier = Modifier.padding(20.dp),
                    icon = R.drawable.ic_close,
                    iconActive = R.drawable.ic_close,
                    onClick = onCloseButton
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (dialog.content.resourceId != 0) {
                    when (dialog.resourceType) {
                        UiDialogModel.FDialogResourceType.IMAGE -> {
                            Image(
                                painter = painterResource(id = dialog.content.resourceId),
                                contentDescription = "Launcher",
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxWidth(0.55f)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }

                        UiDialogModel.FDialogResourceType.LOTTIE -> {

                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                if (dialog.content.titleId.isNotEmpty()) {
                    Text(
                        text = dialog.content.titleId,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (dialog.content.subTitleId.isNotEmpty()) {
                    Text(
                        text = dialog.content.subTitleId,
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 16.dp, bottom = 30.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondary,
                        maxLines = 6,
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                dialog.content.trustedContact?.let { trustedContact ->
                    Text(
                        text = trustedContact,
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 28.dp, bottom = 45.dp)
                            .fillMaxWidth()
                            .clickable { trustedContactListener?.invoke() },
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 6
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    if (dialog.hasButtons()) {
                        when (dialog.quantityButtons) {
                            1 -> {
                                UiSimpleButton(
                                    onClick = onClickButton,
                                    text = dialog.content.buttonTitleId[0],
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        end = 20.dp,
                                        top = 40.dp,
                                        bottom = 20.dp
                                    )
                                )

                            }

                            2 -> {
                                ConstraintLayout(modifier = Modifier.padding(bottom = 20.dp)) {
                                    val (firstButton, secondButton) = createRefs()
                                    when (dialog.content.buttonType[0]) {
                                        UiDialogContentModel.FDialogButtonType.TEXT_BUTTON -> UiTextButton(
                                            onClick = onClickButton,
                                            textColor = if (dialog.content.buttonColor.isNotEmpty()) {
                                                dialog.content.buttonColor[0]
                                            } else {
                                                MaterialTheme.colorScheme.primary
                                            },
                                            text = dialog.content.buttonTitleId[0],
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

                                        UiDialogContentModel.FDialogButtonType.BUTTON -> UiSimpleButton(
                                            onClick = onClickButton,
                                            background = if (dialog.content.buttonColor.isNotEmpty()) {
                                                dialog.content.buttonColor[0]
                                            } else {
                                                MaterialTheme.colorScheme.primary
                                            },
                                            text = dialog.content.buttonTitleId[0],
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
                                        UiDialogContentModel.FDialogButtonType.TEXT_BUTTON -> UiTextButton(
                                            onClick = onClickSecondButton,
                                            textColor = if (dialog.content.buttonColor.size > 1) {
                                                dialog.content.buttonColor[1]
                                            } else {
                                                MaterialTheme.colorScheme.primary
                                            },
                                            text = dialog.content.buttonTitleId[1],
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                                                .constrainAs(secondButton) {
                                                    top.linkTo(firstButton.bottom)
                                                })

                                        UiDialogContentModel.FDialogButtonType.BUTTON -> UiSimpleButton(
                                            onClick = onClickSecondButton,
                                            background = if (dialog.content.buttonColor.size > 1) {
                                                dialog.content.buttonColor[1]
                                            } else {
                                                MaterialTheme.colorScheme.primary
                                            },
                                            text = dialog.content.buttonTitleId[1],
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
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
        }
    }
}


