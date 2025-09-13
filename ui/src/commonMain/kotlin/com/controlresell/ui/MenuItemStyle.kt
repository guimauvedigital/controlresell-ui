package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class MenuItemStyle(
    val labelStyle: TextStyle = TextStyle.Default,
    val label2Style: TextStyle = TextStyle.Default,
    val actionLabelStyle: TextStyle = TextStyle.Default,
)


val DefaultMenuItemStyle
    @Composable get() = MenuItemStyle(
        labelStyle = LocalTypography.current.p,
        label2Style = LocalTypography.current.p,
        actionLabelStyle = LocalTypography.current.p.copy(
            LocalColorScheme.current.onSurfaceVariant
        ),
    )
