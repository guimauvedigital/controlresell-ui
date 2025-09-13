package com.controlresell.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.storytale.story

val Card by story {
    ControlResellTheme {
        val text by parameter("Text")

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            Card {
                Text(text)
            }
            Card(
                style = BorderedCardStyle
            ) {
                Text(text)
            }
            Card(
                style = BorderedCardStyle.copy(
                    roundedCorners = TopRoundedCorners,
                    borderVisibility = BottomBorders
                )
            ) {
                Text(text)
            }
        }
    }
}

val MenuItem by story {
    ControlResellTheme {
        val text by parameter("Text")
        val actionText by parameter("ActionText")

        Column(
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            MenuItem(
                label = text,
                label2 = "Label 2",
                actionLabel = actionText,
                cardStyle = LocalCardStyle.current.copy(
                    roundedCorners = TopRoundedCorners,
                ),
            )
            MenuItem(
                label = text,
                label2 = "Label 2",
                actionLabel = actionText,
                cardStyle = BorderedCardStyle.copy(
                    roundedCorners = BottomRoundedCorners,
                    borderVisibility = TopBorders,
                ),
            )
            Spacer(Modifier.height(16.dp))
            MenuItem(
                label = text,
                label2 = "Label 2",
                actionLabel = actionText,
            )
        }
    }
}
