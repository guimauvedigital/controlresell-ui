package com.controlresell.ui.components

import com.controlresell.ui.theme.ControlResellTheme
import org.jetbrains.compose.storytale.story

val Header by story {
    ControlResellTheme {
        val title by parameter("Title")
        val showBackButton by parameter(true)

        Header(
            title = title,
            leftElement = {
                if (showBackButton) {
                    BackButton(onBack = {})
                }
            }
        )
    }
}
