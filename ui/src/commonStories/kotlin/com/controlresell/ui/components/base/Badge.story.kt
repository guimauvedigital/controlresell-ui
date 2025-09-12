package com.controlresell.ui.components.base

import com.controlresell.ui.theme.AppColors
import org.jetbrains.compose.storytale.story

val `Badge default state` by story {
    val text by parameter("Default")
    val color by parameter(
        listOf(
            AppColors.ForestGreen,
            AppColors.PrincetonOrange
        )
    )

    Badge(
        color = color,
        text = text,
    )
}
