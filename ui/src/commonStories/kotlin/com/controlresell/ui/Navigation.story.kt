package com.controlresell.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    ControlResellTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        NavigationBar {
            NavigationBarItem(
                label = "Home",
                icon = Logo,
                selected = selectedIndex == 0,
                onClick = { selectedIndex = 0 },
                renderIcon = { imageVector, size, color ->
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.width(size * 1.4f).height(size)
                    )
                }
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

val NavigationInScaffold by story {
    ControlResellTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        label = "Home",
                        icon = Logo,
                        selected = selectedIndex == 0,
                        onClick = { selectedIndex = 0 },
                        renderIcon = { imageVector, size, color ->
                            Icon(
                                imageVector = imageVector,
                                contentDescription = null,
                                tint = color,
                                modifier = Modifier.width(size * 1.4f).height(size)
                            )
                        }
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
        ) {
            NavigationHeader(
                title = "Home",
                startElement = {
                    BackButton(onBack = {})
                }
            )
            LazyColumn(
                contentPadding = it,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(100) {
                    Text("Item #$it")
                }
            }
        }
    }
}
