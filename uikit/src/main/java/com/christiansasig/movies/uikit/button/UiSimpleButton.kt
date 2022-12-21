package com.christiansasig.movies.uikit.button

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.christiansasig.movies.uikit.style.MoviesTheme
import com.christiansasig.movies.uikit.style.lightGrey2

@Composable
fun UiSimpleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(25.dp),
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    background: Color = MaterialTheme.colorScheme.onSecondary,
    disabledBackground: Color = lightGrey2,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    @DrawableRes icon: Int? = null,
    tintColorIcon: Color = MaterialTheme.colorScheme.onPrimary,
) {
    TextButton(
        modifier = modifier
            .height(50.dp)
            .indication(
                interactionSource = interactionSource,
                indication = rememberRipple(),
            ),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            containerColor = background,
            disabledContainerColor = disabledBackground
        ),
        shape = shape,
        border = border,
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.let {
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = it),
                    tint = tintColorIcon,
                    contentDescription = null,
                )
            }

            Text(
                text = text,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(
                        Alignment.CenterVertically
                    ),
                color = textColor,
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}

@Preview("default", "buttons")
@Preview("dark theme", "buttons", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ButtonPreview() {
    MoviesTheme {
        UiSimpleButton(onClick = {}, text = "Button")
    }
}
