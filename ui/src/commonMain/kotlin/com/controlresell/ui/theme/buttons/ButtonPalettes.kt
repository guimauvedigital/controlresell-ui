package com.controlresell.ui.theme.buttons

import androidx.compose.ui.graphics.Color
import com.controlresell.ui.theme.AppColors

object ButtonPalettes {

    val Primary = ButtonPalette(
        background = Color.White,
        underlay = AppColors.ChineseSilver,
        textColor = AppColors.ChineseBlack
    )

    val Secondary = ButtonPalette(
        background = AppColors.Inchworm,
        underlay = AppColors.AndroidGreen,
        textColor = Color(0xFF111111)
    )

    val SecondaryInverted = ButtonPalette(
        background = Color(0x30BFF05F), // rgba(191,238,97,0.16)
        underlay = Color(0x1AA5D641),  // rgba(165,214,65,0.10)
        textColor = Color(0xFFBFF05F)
    )

}
