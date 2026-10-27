package com.denalal.dayly.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class DaylyDisabledColors(
    val container: Color,
    val content: Color,
)

val LocalDaylyDisabledColors = staticCompositionLocalOf {
    DaylyDisabledColors(
        container = DaylyDisabledLight,
        content = DaylyDisabledLightContent,
    )
}