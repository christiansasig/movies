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
            val (imageReference, titleReference) = createRefs()
            UiImage(
                modifier = Modifier
                    .constrainAs(imageReference) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .width(60.dp),
                imageUrl = imageUrl
            )

            UiTextRegular(
                modifier = Modifier
                    .padding(start = 5.dp),
                color = color,
                style = styleDescription,
                text = likes
            )
        }
    }
}