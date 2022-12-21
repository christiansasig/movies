package com.christiansasig.movies.uikit.style

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme =
        darkColorScheme(
                background = Black,
                onBackground = White,
                primary = Purple40,
                onPrimary = White,
                secondary = Grey40,
                onSecondary = Purple40,
                onTertiary = White,
                tertiary = Pink80,
                surfaceVariant = Black,
                onSurface = White,
                onSurfaceVariant = White
        )

private val LightColorScheme =
        lightColorScheme(
                background = White,
                onBackground = Black,
                primary = Purple40,
                onPrimary = White,
                secondary = Grey40,
                onSecondary = Purple40,
                tertiary = Pink40,
                onTertiary = Grey60,
                surfaceVariant = White, // Color para el fondo del spinner
                onSurface = Grey40,
                onSurfaceVariant = Grey40

                /* Other default colors to override
                background = Color(0xFFFFFBFE),
                surface = Color(0xFFFFFBFE),
                onPrimary = Color.White,
                onSecondary = Color.White,
                onTertiary = Color.White,
                onBackground = Color(0xFF1C1B1F),
                onSurface = Color(0xFF1C1B1F),
                */
                )

@Composable
fun MoviesTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        // Dynamic color is available on Android 12+
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
) {
    val colorScheme =
            when {
                dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                    val context = LocalContext.current
                    if (darkTheme) dynamicDarkColorScheme(context)
                    else dynamicLightColorScheme(context)
                }
                darkTheme -> DarkColorScheme
                else -> LightColorScheme
            }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.background.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
