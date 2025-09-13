package com.controlresell.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.Check
import com.adamglin.phosphoricons.bold.PaperPlaneTilt
import com.adamglin.phosphoricons.bold.Sliders
import com.controlresell.ui.theme.*
import org.jetbrains.compose.storytale.story

val Button by story(group = "Buttons") {
    ControlResellTheme {
        val text by parameter("Main Button")
        val enabled by parameter(true)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                text = text,
                enabled = enabled,
                onClick = {},
            )
            Button(
                text = text,
                style = PrimaryButtonStyle,
                enabled = enabled,
                onClick = {},
            )
            Button(
                text = text,
                style = SecondaryButtonStyle,
                enabled = enabled,
                onClick = {},
            )
            Button(
                text = text,
                style = SecondaryInvertedButtonStyle,
                enabled = enabled,
                onClick = {},
            )
        }
    }
}

val OptionButton by story(group = "Buttons") {
    ControlResellTheme {
        val text by parameter("Option Button")
        val enabled by parameter(true)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OptionButton(
                icon = PhosphorIcons.Bold.PaperPlaneTilt,
                enabled = enabled,
                onClick = {},
            )
            OptionButton(
                text = text,
                enabled = enabled,
                onClick = {},
            )
            OptionButton(
                icon = PhosphorIcons.Bold.PaperPlaneTilt,
                text = text,
                enabled = enabled,
                onClick = {},
            )
            OptionButton(
                icon = PhosphorIcons.Bold.Sliders,
                text = text,
                enabled = enabled,
                onClick = {},
            )
            OptionButton(
                icon = PhosphorIcons.Bold.Check,
                text = text,
                enabled = enabled,
                onClick = {},
            )
            OptionButton(
                enabled = enabled,
                onClick = {},
                style = SelectedOptionButtonStyle
            )
            OptionButton(
                text = text,
                enabled = enabled,
                onClick = {},
                style = SelectedOptionButtonStyle
            )
        }
    }
}
