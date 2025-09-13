package com.controlresell.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Immutable
data class BadgeStyle(
    val backgroundColor: Color = Color.Unspecified,
    val textStyle: TextStyle = TextStyle.Default,
    val descriptionTextStyle: TextStyle = TextStyle.Default,
    val shape: Shape = RectangleShape,
)

val DefaultBadgeStyle
    @Composable get() = BadgeStyle(
        backgroundColor = LocalColorScheme.current.surface,
        textStyle = LocalTypography.current.p,
        descriptionTextStyle = LocalTypography.current.p.copy(color = LocalColorScheme.current.onSurfaceVariant),
        shape = RoundedCornerShape(50),
    )
