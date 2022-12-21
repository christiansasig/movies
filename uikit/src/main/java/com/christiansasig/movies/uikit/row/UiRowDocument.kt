package com.christiansasig.movies.uikit.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.christiansasig.movies.core.R
import com.christiansasig.movies.uikit.button.UiTextButton
import com.christiansasig.movies.uikit.iconbutton.UiIconButton
import com.christiansasig.movies.uikit.image.UiImage
import com.christiansasig.movies.uikit.text.UiTextRegular
import com.christiansasig.movies.uikit.text.UiTextSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiRowDocument(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    styleTitle: TextStyle = MaterialTheme.typography.titleMedium,
    styleDescription: TextStyle = MaterialTheme.typography.titleSmall,
    title: String,
    description: String,
    likes: String = "",
    imageUrl: String?,
    onClick: () -> Unit = {},
    onClickButton: () -> Unit = {},
    onClickShareButton: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick
            },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            val (imageReference, titleReference, descriptionReference, share, details) = createRefs()
            UiImage(
                modifier = Modifier
                    .constrainAs(imageReference) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .height(100.dp),
                imageUrl = imageUrl
            )

            Row(modifier = Modifier
                .constrainAs(share) {
                    end.linkTo(parent.end)
                    top.linkTo(imageReference.bottom)
                }
                .padding(top = 8.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    UiIconButton(
                        modifier = Modifier.padding(5.dp),
                        icon = R.drawable.ic_like,
                        iconActive = R.drawable.ic_like,
                        onClick = {

                        }
                    )
                    UiTextRegular(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        color = color,
                        style = styleDescription,
                        text = likes
                    )
                }


                UiIconButton(
                    modifier = Modifier.padding(5.dp),
                    icon = R.drawable.ic_share,
                    iconActive = R.drawable.ic_share,
                    onClick = onClickShareButton
                )
            }

            UiTextSemiBold(
                modifier = Modifier
                    .constrainAs(titleReference) {
                        start.linkTo(parent.start)
                        end.linkTo(share.start)
                        top.linkTo(imageReference.bottom)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 10.dp),
                maxLines = 2,
                color = color,
                style = styleTitle,
                text = title
            )

            UiTextRegular(
                modifier = Modifier
                    .constrainAs(descriptionReference) {
                        start.linkTo(parent.start)
                        top.linkTo(titleReference.bottom)
                    }
                    .padding(top = 10.dp),
                maxLines = 3,
                color = color,
                style = styleDescription,
                text = description
            )

            UiTextButton(
                modifier = Modifier
                    .constrainAs(details) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(descriptionReference.bottom)
                    },
                onClick = onClickButton,
                text = stringResource(id = R.string.close),
            )

        }
    }
}