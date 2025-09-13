package com.controlresell.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.storytale.story

val Input by story {
    ControlResellTheme {
        var value by parameter("Input text")
        val placeholder by parameter("Hint text")
        val label by parameter<String?>(null)
        val successMsg by parameter<String?>(null)
        val errorMsg by parameter<String?>(null)

        Input(
            value = value,
            onValueChange = { value = it },
            placeholder = placeholder,
            label = label,
            successMessage = successMsg,
            errorMessage = errorMsg,
            modifier = Modifier.padding(16.dp)
        )
    }
}

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
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
