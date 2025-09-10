package com.controlresell.ui.components.buttons

import com.controlresell.ui.theme.buttons.ButtonPalettes
import org.jetbrains.compose.storytale.story

val `MainButton default state` by story {
    val text by parameter("Main Button")
    val type by parameter(
        listOf(
            ButtonPalettes.Primary,
            ButtonPalettes.Secondary,
            ButtonPalettes.SecondaryInverted,
        )
    )
    val enabled by parameter(true)

    MainButton(
        text = text,
        type = type,
        enabled = enabled,
        onClick = {},
    )
}
