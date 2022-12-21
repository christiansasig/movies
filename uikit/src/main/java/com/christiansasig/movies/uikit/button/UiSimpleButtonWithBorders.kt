package com.christiansasig.movies.uikit.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.christiansasig.movies.uikit.style.Purple40

@Composable
fun UiSimpleButtonWithBorders(
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(2.dp, Purple40),
    shape: Shape = RoundedCornerShape(20.dp),
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSecondary,
    @DrawableRes icon: Int? = null,
    tintColorIcon: Color = MaterialTheme.colorScheme.tertiary,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(50.dp),
        border = border,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(
                        Alignment.CenterVertically
                    ),
                color = textColor,
                style = MaterialTheme.typography.bodySmall
            )
            icon?.let {
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = it),
                    tint = tintColorIcon,
                    contentDescription = null,
                )
            }
        }
    }
}