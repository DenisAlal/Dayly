package com.denalal.dayly.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/*
 * Палитра Dayly.
 *
 * Светлая тема                          Тёмная тема
 * Surface   #F7F4EE  (тёплый песок)     #0F1512  (ночной фон)
 * Card      #FFFFFF                     #18201B  (приподнятая поверхность)
 * Ink       #1C2B24                     #E9F0EA
 * Muted     #6E7C74                     #93A399
 * Primary   #4F7C63                     #8FC3A6  (светлее — иначе не хватает контраста)
 * Secondary #3C6350                     #A9C7B7
 * Accent    #E39A6E  (тёплый)           #E8A87C
 * Outline   #E7E1D7                     #29332C
 */

// --- светлая палитра --------------------------------------------------------

val DaylySage = Color(0xFF4F7C63)
val DaylySageDark = Color(0xFF3C6350)
val DaylySageSoft = Color(0xFFE3EFE7)
val DaylySageGhost = Color(0xFFF0F6F2)
val DaylyCream = Color(0xFFF7F4EE)
val DaylyInk = Color(0xFF1C2B24)
val DaylyStone = Color(0xFF6E7C74)
val DaylySand = Color(0xFFE7E1D7)
val DaylyWarm = Color(0xFFE39A6E)

val DaylyDisabledLight = Color(0xFFD8E3DB)

val DaylyDisabledLightContent = Color(0xFF57675D)

// --- тёмная палитра ---------------------------------------------------------

val DaylySageLight = Color(0xFF8FC3A6)
val DaylySageMid = Color(0xFFA9C7B7)
val DaylyNight = Color(0xFF0F1512)
val DaylyNightSurface = Color(0xFF18201B)
val DaylyNightElevated = Color(0xFF1F2A23)
val DaylyNightLine = Color(0xFF29332C)
val DaylyMist = Color(0xFF93A399)
val DaylySnow = Color(0xFFE9F0EA)

val DaylyDisabledDark = Color(0xFF27362D)

val DaylyDisabledDarkContent = Color(0xFF9DB0A4)

// --- схемы ------------------------------------------------------------------

val DaylyLightColors = lightColorScheme(
    primary = DaylySage,
    onPrimary = Color.White,
    primaryContainer = DaylySageSoft,
    onPrimaryContainer = DaylyInk,
    secondary = DaylySageDark,
    onSecondary = Color.White,
    secondaryContainer = DaylySageGhost,
    onSecondaryContainer = DaylySageDark,
    tertiary = DaylyWarm,
    onTertiary = DaylyInk,
    background = DaylyCream,
    onBackground = DaylyInk,
    surface = Color.White,
    onSurface = DaylyInk,
    surfaceVariant = DaylySageGhost,
    onSurfaceVariant = DaylyStone,
    outline = DaylySand,
    outlineVariant = DaylySand,
    error = Color(0xFFB3261E),
    onError = Color.White,
)

val DaylyDarkColors = darkColorScheme(
    primary = DaylySageLight,
    onPrimary = DaylyNight,
    primaryContainer = DaylyNightElevated,
    onPrimaryContainer = DaylySnow,
    secondary = DaylySageMid,
    onSecondary = DaylyNight,
    secondaryContainer = DaylyNightElevated,
    onSecondaryContainer = DaylySnow,
    tertiary = Color(0xFFE8A87C),
    onTertiary = DaylyNight,
    background = DaylyNight,
    onBackground = DaylySnow,
    surface = DaylyNightSurface,
    onSurface = DaylySnow,
    surfaceVariant = DaylyNightElevated,
    onSurfaceVariant = DaylyMist,
    outline = DaylyNightLine,
    outlineVariant = DaylyNightLine,
    error = Color(0xFFF2B8B5),
    onError = DaylyNight,
)