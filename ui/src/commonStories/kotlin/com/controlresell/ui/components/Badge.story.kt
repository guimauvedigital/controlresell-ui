package com.controlresell.ui.components

import com.controlresell.ui.theme.ControlResellTheme
import com.controlresell.ui.theme.ForestGreen
import com.controlresell.ui.theme.PrincetonOrange
import org.jetbrains.compose.storytale.story

val Badge by story {
    ControlResellTheme {
        val text by parameter("Default")
        val color by parameter(
            listOf(
                ForestGreen,
                PrincetonOrange
            )
        )

        Badge(
            color = color,
            text = text,
        )
    }
}
