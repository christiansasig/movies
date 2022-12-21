package com.christiansasig.movies.uikit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.christiansasig.movies.uikit.style.lightGrey2

@Composable
fun UiTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(12.dp),
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    textColor: Color = MaterialTheme.colorScheme.onSecondary,
    disabledTextColor: Color = lightGrey2,
    text: String
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
        shape = shape,
        border = border,
    ) {
        ProvideTextStyle(
            value = MaterialTheme.typography.bodySmall,
        ) {
            Text(
                text = text,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                color = if (enabled) textColor else disabledTextColor,
            )
        }
    }
}
