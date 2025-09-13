package com.controlresell.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ControlResellColorScheme(
    val primary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val onPrimaryVariant: Color = Color.Unspecified,
    val onSecondaryVariant: Color = Color.Unspecified,
    val onBackgroundVariant: Color = Color.Unspecified,
    val onSurfaceVariant: Color = Color.Unspecified,
    val success: Color = Color.Unspecified,
    val warning: Color = Color.Unspecified,
    val error: Color = Color.Unspecified,
)

val LightColorScheme = ControlResellColorScheme(
    primary = Inchworm,
    secondary = LavenderIndigo,
    background = ChineseBlack,
    surface = CharlestonGreen,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
    onPrimaryVariant = DavysGray,
    onSecondaryVariant = DavysGray,
    onBackgroundVariant = DavysGray,
    onSurfaceVariant = DavysGray,
    success = Success,
    warning = Warning.W500,
    error = Error,
)

val DarkColorScheme = ControlResellColorScheme(
    primary = Inchworm,
    secondary = LavenderIndigo,
    background = ChineseBlack,
    surface = CharlestonGreen,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
    onPrimaryVariant = DavysGray,
    onSecondaryVariant = DavysGray,
    onBackgroundVariant = DavysGray,
    onSurfaceVariant = DavysGray,
    success = Success,
    warning = Warning.W500,
    error = Error,
)
