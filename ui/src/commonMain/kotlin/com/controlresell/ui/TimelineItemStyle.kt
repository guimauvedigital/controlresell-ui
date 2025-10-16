package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class TimelineItemStyle(
    val dotColor: Color = Color.Unspecified,
    val lineColor: Color = Color.Unspecified,
    val dotSize: Dp = 12.dp,
    val lineWidth: Dp = 2.dp,
    val timeTextStyle: TextStyle = TextStyle.Default,
    val titleTextStyle: TextStyle = TextStyle.Default,
    val subtitleTextStyle: TextStyle = TextStyle.Default,
    val chevronColor: Color = Color.Unspecified,
)

val DefaultTimelineItemStyle: TimelineItemStyle
    @Composable get() {
        val colorScheme = LocalColorScheme.current
        val typography = LocalTypography.current

        return TimelineItemStyle(
            dotColor = colorScheme.primary,
            lineColor = colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
            dotSize = 12.dp,
            lineWidth = 2.dp,
            timeTextStyle = typography.labelSmall.copy(color = colorScheme.onSurfaceVariant),
            titleTextStyle = typography.p.copy(color = colorScheme.onBackground),
            subtitleTextStyle = typography.labelSmall.copy(color = colorScheme.onSurfaceVariant),
            chevronColor = colorScheme.onSurfaceVariant,
        )
    }
