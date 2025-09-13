package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ActivityIndicatorStyle(
    val color: Color = Color.Unspecified,
    val segmentCount: Int = 8,
    val animationDuration: Int = 1000,
)

val DefaultActivityIndicatorStyle
    @Composable get() = ActivityIndicatorStyle(
        color = LocalColorScheme.current.onSurfaceVariant,
        segmentCount = 8,
        animationDuration = 1000,
    )
