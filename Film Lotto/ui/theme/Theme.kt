package com.example.filmlotto.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color


@Composable
fun FilmLottoTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Gold,
            secondary = PurpleDark,
            background = DarkBackground,
            surface = DarkBackground,
            onPrimary = TextLight,
            onBackground = TextLight,
            onSurface = TextLight
        )
    } else {
        lightColorScheme(
            primary = Yellow,
            secondary = PinkLight,
            background = LightBackground,
            surface = LightBackground,
            onPrimary = TextDark,
            onBackground = TextDark,
            onSurface = TextDark
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}