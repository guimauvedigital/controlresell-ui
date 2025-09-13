package com.controlresell.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

val LocalColorScheme = compositionLocalOf { ControlResellColorScheme() }
val LocalTypography = compositionLocalOf { ControlResellTypography() }
val LocalDefaultTextStyle = compositionLocalOf { TextStyle.Default }
val LocalButtonStyle = compositionLocalOf { ButtonStyle() }
val LocalOptionButtonStyle = compositionLocalOf { OptionButtonStyle() }
val LocalInputStyle = compositionLocalOf { InputStyle() }
val LocalBadgeStyle = compositionLocalOf { BadgeStyle() }

@Composable
fun ControlResellTheme(
    colors: ControlResellColorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme,
    content: @Composable () -> Unit,
) {

    CompositionLocalProvider(
        LocalColorScheme provides colors,
    ) {
        CompositionLocalProvider(
            LocalTypography provides Typography,
        ) {
            CompositionLocalProvider(
                LocalDefaultTextStyle provides LocalTypography.current.p,
                LocalButtonStyle provides PrimaryButtonStyle,
                LocalOptionButtonStyle provides DefaultOptionButtonStyle,
                LocalInputStyle provides DefaultInputStyle,
                LocalBadgeStyle provides DefaultBadgeStyle,
            ) {
                Box(Modifier.fillMaxSize().background(colors.background)) {
                    content()
                }
            }
        }
    }

}
