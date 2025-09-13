package com.controlresell.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.ControlResellTheme
import org.jetbrains.compose.storytale.story

val MenuItem by story {
    ControlResellTheme {
        val text by parameter("Text")
        val actionText by parameter("ActionText")

        Column {
            MenuItem(
                label = text,
                label2 = "Label 2" to null,
                actionLabel = actionText,
                hasBorderRadiusPerSide = BorderRadiusPerSide.Companion.topFull,
                hideBorderPerSide = BorderPerSide.Companion.topFull
            )
            MenuItem(
                label = text,
                label2 = "Label 2" to null,
                actionLabel = actionText,
                hasBorderRadiusPerSide = BorderRadiusPerSide.Companion.bottomFull
            )
            Spacer(Modifier.height(16.dp))
            MenuItem(
                label = text,
                label2 = "Label 2" to null,
                actionLabel = actionText,
                hasBorderRadiusPerSide = BorderRadiusPerSide.Companion.full
            )
        }
    }
}
