package com.controlresell.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.Check
import com.adamglin.phosphoricons.bold.PaperPlaneTilt
import com.adamglin.phosphoricons.bold.Sliders
import org.jetbrains.compose.storytale.story

val Button by story {
    ControlResellTheme {
        val text by parameter("Main Button")
        val enabled by parameter(true)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            Button(
                text = text,
                style = PrimaryButtonStyle,
                enabled = enabled,
                onClick = {},
            )
            Button(
                text = text,
                style = PrimaryTransparentButtonStyle,
                enabled = enabled,
                onClick = {},
            )
            Button(
                text = text,
                style = SecondaryButtonStyle,
                enabled = enabled,
                onClick = {},
            )
        }
    }
}

val OptionButton by story {
    ControlResellTheme {
        val text by parameter("Option Button")
        val enabled by parameter(true)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
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
            OptionButton(
                text = text,
                enabled = enabled,
                onClick = {},
                style = DraftStatusOptionButtonStyle
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = PendingStatusOptionButtonStyle(false)
                )
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = PendingStatusOptionButtonStyle(true)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = ProgressStatusOptionButtonStyle(false)
                )
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = ProgressStatusOptionButtonStyle(true)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = DoneStatusOptionButtonStyle(false)
                )
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = DoneStatusOptionButtonStyle(true)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = ErrorStatusOptionButtonStyle(false)
                )
                OptionButton(
                    text = text,
                    enabled = enabled,
                    onClick = {},
                    style = ErrorStatusOptionButtonStyle(true)
                )
            }
        }
    }
}

val Badge by story {
    ControlResellTheme {
        val text by parameter("Default")

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Badge(
                color = ForestGreen,
                text = text,
            )
            Badge(
                color = PrincetonOrange,
                text = text,
            )
            Badge(
                color = ForestGreen,
                text = text,
                style = RoundedBadgeStyle,
            )
            Badge(
                color = PrincetonOrange,
                text = text,
                style = RoundedBadgeStyle,
            )
        }
    }
}
