package com.controlresell.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ButtonStyle(
    val backgroundColor: Color = Color.Unspecified,
    val underlayColor: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
)

val PrimaryButtonStyle = ButtonStyle(
    backgroundColor = White,
    underlayColor = ChineseSilver,
    textColor = ChineseBlack
)

val SecondaryButtonStyle = ButtonStyle(
    backgroundColor = Inchworm,
    underlayColor = AndroidGreen,
    textColor = Color(0xFF111111)
)

val SecondaryInvertedButtonStyle = ButtonStyle(
    backgroundColor = Color(0x30BFF05F), // rgba(191,238,97,0.16)
    underlayColor = Color(0x1AA5D641),  // rgba(165,214,65,0.10)
    textColor = Color(0xFFBFF05F)
)
