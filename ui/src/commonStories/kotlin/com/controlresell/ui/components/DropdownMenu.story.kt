package com.controlresell.ui.components

import androidx.compose.ui.graphics.Color
import com.controlresell.ui.theme.ControlResellTheme
import org.jetbrains.compose.storytale.story

val DropdownMenu by story {
    ControlResellTheme {
        DropdownMenu(
            options = listOf(
                DropdownMenuOption(
                    id = "1",
                    label = "Option 1",
                    data = 1,
                    isDefaultSelected = true,
                    onPress = { selected, _ -> println("Option 1 selected: $selected") }
                ),
                DropdownMenuOption(
                    id = "2",
                    label = "Option 2",
                    data = 2,
                    isPopular = true,
                    onPress = { selected, _ -> println("Option 2 selected: $selected") }
                ),
                DropdownMenuOption(
                    id = "3",
                    label = "Option 3",
                    data = 3,
                    isBlocked = true,
                    onPress = { selected, blocked -> println("Option 3 blocked: $blocked") }
                ),
                DropdownMenuOption(
                    id = "4",
                    label = "Option 4",
                    data = 4,
                    onPress = { selected, _ -> println("Option 4 selected: $selected") }
                )
            ),
            label = "Select an option",
            maxSelection = 2,
            enableSearch = true,
            forceAtLeastOneSelection = true,
            closeOnSelectionWhenMaxOneSelection = true,
            onElementSelected = { selected, all ->
                println("Selected: ${selected.map { it.label }} / All selection: ${all.map { it.label }}")
            },
            onElementRemoved = { removed, all ->
                println("Removed: ${removed.map { it.label }} / Remaining: ${all.map { it.label }}")
            },
            onBlockedElementPressed = { blocked ->
                println("Blocked element pressed: ${blocked.label}")
            },
            getColorForSelection = { value, _ ->
                if (value.isPopular) Color.Green else Color.White
            }
        )
    }
}
