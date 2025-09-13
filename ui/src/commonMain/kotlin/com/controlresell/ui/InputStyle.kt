package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Immutable
data class InputStyle(
    val backgroundColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val focusBorderColor: Color = Color.Unspecified,
    val textStyle: TextStyle = TextStyle.Default,
    val labelTextStyle: TextStyle = TextStyle.Default,
    val placeholderTextStyle: TextStyle = TextStyle.Default,
)

val DefaultInputStyle
    @Composable get() = InputStyle(
        backgroundColor = LocalColorScheme.current.surface,
        borderColor = LocalColorScheme.current.surface,
        focusBorderColor = DavysGray,
        textStyle = LocalTypography.current.p,
        labelTextStyle = LocalTypography.current.label,
        placeholderTextStyle = LocalTypography.current.p.copy(color = LocalColorScheme.current.onSurfaceVariant),
    )
