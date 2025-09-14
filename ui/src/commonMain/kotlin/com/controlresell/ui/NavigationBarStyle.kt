package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class NavigationBarStyle(
    val backgroundColor: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val selectedTextColor: Color = Color.Unspecified,
    val iconSize: Dp = 28.dp,
    val blurRadius: Dp = 20.dp,
)

val DefaultNavigationBarStyle
    @Composable get() = NavigationBarStyle(
        backgroundColor = LocalColorScheme.current.background.copy(alpha = 0.45f),
        textColor = PhilippineSilver,
        selectedTextColor = LocalColorScheme.current.primary,
        iconSize = 28.dp,
        blurRadius = 20.dp,
    )
