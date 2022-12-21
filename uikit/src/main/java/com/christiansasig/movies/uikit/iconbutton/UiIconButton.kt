package com.christiansasig.movies.uikit.iconbutton

import android.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun UiIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    tintColorIconActive: Color = MaterialTheme.colorScheme.onSecondary,
    tintColorIcon: Color = MaterialTheme.colorScheme.onSecondary,
    @DrawableRes icon: Int = R.drawable.arrow_up_float,
    @DrawableRes iconActive: Int = R.drawable.arrow_up_float
) {
    IconButton(modifier = modifier.then(Modifier.size(20.dp)),
        onClick = {
            onClick()
        }) {
        Icon(
            painter = painterResource(
                if (selected) {
                    iconActive
                } else {
                    icon
                }),
            tint = if (selected){
                tintColorIconActive
            }else{
                tintColorIcon
            },
            contentDescription = null)
    }
}