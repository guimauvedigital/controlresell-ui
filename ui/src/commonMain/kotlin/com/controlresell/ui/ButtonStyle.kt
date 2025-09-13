package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ButtonStyle(
    val backgroundColor: Color = Color.Unspecified,
    val underlayColor: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
)

val PrimaryButtonStyle
    @Composable get() = ButtonStyle(
        backgroundColor = LocalColorScheme.current.primary,
        underlayColor = AndroidGreen,
        textColor = Color(0xFF111111)
    )

val PrimaryTransparentButtonStyle = ButtonStyle(
    backgroundColor = Color(0x30BFF05F), // rgba(191,238,97,0.16)
    underlayColor = Color(0x1AA5D641),  // rgba(165,214,65,0.10)
    textColor = Color(0xFFBFF05F)
)

val SecondaryButtonStyle
    @Composable get() = ButtonStyle(
        backgroundColor = White,
        underlayColor = ChineseSilver,
        textColor = LocalColorScheme.current.background
    )
