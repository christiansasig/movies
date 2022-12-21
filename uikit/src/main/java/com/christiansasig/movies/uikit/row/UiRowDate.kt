package com.christiansasig.movies.uikit.row

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.christiansasig.movies.core.R
import com.christiansasig.movies.uikit.text.UiTextHeadingBoldTitle
import com.christiansasig.movies.uikit.text.UiTextRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiRowDate(
    modifier: Modifier = Modifier,
    colorBackground: Color = MaterialTheme.colorScheme.background,
    color: Color = MaterialTheme.colorScheme.onBackground,
    styleTitle: TextStyle = MaterialTheme.typography.bodyMedium,
    styleDescription: TextStyle = MaterialTheme.typography.bodySmall,
    title: String,
    description: String? = null,
    date: String,
    time: String,
    @DrawableRes image: Int = R.drawable.ic_calendar,
    onClick: (Uri?) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(colorBackground)
            .clickable {
                onClick
            },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            val (titleReference, dateReference, timeReference, descriptionReference) = createRefs()

            Row(modifier = Modifier
                .constrainAs(titleReference) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(
                        id = image
                    ),
                    contentDescription = null,
                )
                UiTextHeadingBoldTitle(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    maxLines = 2,
                    textAlign = TextAlign.Start,
                    color = color,
                    style = styleTitle,
                    text = title
                )
            }

            UiTextRegular(
                modifier = Modifier
                    .constrainAs(dateReference) {
                        start.linkTo(parent.start)
                        top.linkTo(titleReference.bottom)
                    }
                    .padding(top = 5.dp),
                maxLines = 3,
                color = color,
                style = styleDescription,
                text = date
            )

            UiTextRegular(
                modifier = Modifier
                    .constrainAs(timeReference) {
                        start.linkTo(parent.start)
                        top.linkTo(dateReference.bottom)
                    }
                    .padding(top = 5.dp),
                maxLines = 3,
                color = color,
                style = styleDescription,
                text = time
            )

            description?.let {
                UiTextRegular(
                    modifier = Modifier
                        .constrainAs(descriptionReference) {
                            start.linkTo(parent.start)
                            top.linkTo(timeReference.bottom)
                        }
                        .padding(top = 5.dp),
                    maxLines = 3,
                    color = color,
                    style = styleDescription,
                    text = time
                )
            }
        }
    }
}