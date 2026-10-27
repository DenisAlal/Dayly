package com.denalal.dayly.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

/**
 * Тема Dayly.
 *
 * @param darkTheme какая тема активна. По умолчанию — системная.
 * Когда пользователь выбирает Light/Dark вручную, сюда передаётся false/true,
 * и `isSystemInDarkTheme()` больше не учитывается.
 *
 * @param dynamicColor по умолчанию выключен: динамические цвета Android 12+
 * подменяют брендовую палитру цветами обоев.
 */
@Composable
fun DaylyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DaylyDarkColors
        else -> DaylyLightColors
    }

    CompositionLocalProvider(
        LocalDaylyDisabledColors provides if (darkTheme) {
            DaylyDisabledColors(DaylyDisabledDark, DaylyDisabledDarkContent)
        } else {
            DaylyDisabledColors(DaylyDisabledLight, DaylyDisabledLightContent)
        },
        LocalSpacing provides DaylySpacing(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = DaylyShapes,
            content = content,
        )
    }
}