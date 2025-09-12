package com.controlresell.ui.components.base

import com.controlresell.ui.components.buttons.BackButton
import org.jetbrains.compose.storytale.story

val `CustomHeader default state` by story {
    val title by parameter("Title")
    val showBackButton by parameter(true)

    CustomHeader(
        title = title,
        leftElement = {
            if (showBackButton) {
                BackButton(onBack = {})
            }
        }
    )
}
