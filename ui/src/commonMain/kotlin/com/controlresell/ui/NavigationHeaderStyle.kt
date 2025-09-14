package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Immutable
data class NavigationHeaderStyle(
    val titleStyle: TextStyle = TextStyle.Default,
    val backgroundColor: Color = Color.Unspecified,
)

val DefaultNavigationHeaderStyle
    @Composable get() = NavigationHeaderStyle(
        titleStyle = LocalTypography.current.h6,
        backgroundColor = LocalColorScheme.current.background,
    )
