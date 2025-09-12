package com.controlresell.ui.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.storytale.story

val `MenuItem default state` by story {
    val text by parameter("Text")
    val actionText by parameter("ActionText")

    Column {
        MenuItem(
            label = text,
            label2 = "Label 2" to null,
            actionLabel = actionText,
            hasBorderRadiusPerSide = BorderRadiusPerSide.topFull,
            hideBorderPerSide = BorderPerSide.topFull
        )
        MenuItem(
            label = text,
            label2 = "Label 2" to null,
            actionLabel = actionText,
            hasBorderRadiusPerSide = BorderRadiusPerSide.bottomFull
        )
        Spacer(Modifier.height(16.dp))
        MenuItem(
            label = text,
            label2 = "Label 2" to null,
            actionLabel = actionText,
            hasBorderRadiusPerSide = BorderRadiusPerSide.full
        )
    }
}
