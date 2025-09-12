package com.controlresell.ui.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.storytale.story

val `MenuItem default state` by story {

    Column {
        MenuItem(
            label = "Menu Item",
            label2 = "Label 2" to null,
            actionLabel = "Action",
            hasBorderRadiusPerSide = BorderRadiusPerSide.topFull
        )
        MenuItem(
            label = "Menu Item",
            label2 = "Label 2" to null,
            actionLabel = "Action",
        )
        MenuItem(
            label = "Menu Item",
            label2 = "Label 2" to null,
            actionLabel = "Action",
            hasBorderRadiusPerSide = BorderRadiusPerSide.bottomFull
        )
        Spacer(Modifier.height(16.dp))
        MenuItem(
            label = "Menu Item",
            label2 = "Label 2" to null,
            actionLabel = "Action",
            hasBorderRadiusPerSide = BorderRadiusPerSide.full
        )
    }
}
