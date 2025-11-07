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

val Banner by story {
    ControlResellTheme {
        val title by parameter("Banner Title")
        val description by parameter("This is a banner description")

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            Banner(
                style = WarningBannerStyle,
                title = title,
                description = description,
            )
            Banner(
                style = ErrorBannerStyle,
                title = title,
                description = description,
            )
            Banner(
                style = SuccessBannerStyle,
                title = title,
                description = description,
            )
            Banner(
                style = InfoBannerStyle,
                title = title,
                description = description,
            )
        }
    }
}

val Timeline by story {
    ControlResellTheme {
        val title by parameter("Create a services site 2015-09-01")
        val subtitle by parameter("22 secondes")
        val time by parameter("18:12:44")

        Column(
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            TimelineItem(
                leftText = time,
                title = title,
                subtitle = subtitle,
                dotColor = LocalColorScheme.current.success,
                onClick = {}
            )
            TimelineItem(
                leftText = "18:15:32",
                title = "Deploy to production",
                subtitle = "45 secondes",
                dotColor = LocalColorScheme.current.warning,
                onClick = {}
            )
            TimelineItem(
                leftText = "18:20:15",
                title = "Run tests",
                subtitle = "1 minute",
                dotColor = LocalColorScheme.current.primary,
                onClick = {}
            )
            TimelineItem(
                leftText = "18:25:00",
                title = "Build completed",
                dotColor = LocalColorScheme.current.error,
                showLine = false,
                onClick = {}
            )
        }
    }
}
