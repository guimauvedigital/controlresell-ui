package com.controlresell.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.fill.Envelope
import com.adamglin.phosphoricons.fill.Receipt
import com.adamglin.phosphoricons.fill.TrendUp
import org.jetbrains.compose.storytale.story

val NavigationHeader by story {
    ControlResellTheme {
        val title by parameter("Title")
        val showBackButton by parameter(true)

        NavigationHeader(
            title = title,
            startElement = {
                if (showBackButton) {
                    BackButton(onBack = {})
                }
            }
        )
    }
}

val NavigationBar by story {
    var selectedIndex by remember { mutableStateOf(0) }

    ControlResellTheme {
        NavigationBar {
            NavigationBarItem(
                label = "Home",
                icon = Logo,
                selected = selectedIndex == 0,
                onClick = { selectedIndex = 0 }
            )
            NavigationBarItem(
                label = "Results",
                icon = PhosphorIcons.Fill.TrendUp,
                selected = selectedIndex == 1,
                onClick = { selectedIndex = 1 }
            )
            NavigationBarItem(
                label = "Fees",
                icon = PhosphorIcons.Fill.Receipt,
                selected = selectedIndex == 2,
                onClick = { selectedIndex = 2 }
            )
            NavigationBarItem(
                label = "Messages",
                icon = PhosphorIcons.Fill.Envelope,
                selected = selectedIndex == 3,
                onClick = { selectedIndex = 3 }
            )
        }
    }

}
