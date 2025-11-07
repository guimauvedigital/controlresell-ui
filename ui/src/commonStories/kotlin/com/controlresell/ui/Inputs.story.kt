package com.controlresell.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

val Checkbox by story {
    ControlResellTheme {
        val text by parameter("Checkbox")
        var checked by parameter(false)
        val enabled by parameter(true)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Checkbox(
                value = checked,
                onValueChange = { checked = it },
                enabled = enabled,
                shape = CheckboxShape.Circle,
            )
            Checkbox(
                value = checked,
                onValueChange = { checked = it },
                enabled = enabled,
                shape = CheckboxShape.Square,
            )
            Checkbox(
                value = checked,
                onValueChange = { checked = it },
                enabled = enabled,
                shape = CheckboxShape.Circle,
                label = { Text(text) }
            )
            Checkbox(
                value = checked,
                onValueChange = { checked = it },
                enabled = enabled,
                shape = CheckboxShape.Square,
                label = { Text(text) }
            )
        }
    }
}

val Switch by story {
    ControlResellTheme {
        val text by parameter("Switch")
        var checked by parameter(false)
        val enabled by parameter(true)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Switch(
                value = checked,
                onValueChange = { checked = it },
                enabled = enabled
            )
            Switch(
                value = checked,
                onValueChange = { checked = it },
                enabled = enabled,
                label = { Text(text) }
            )
        }
    }
}

data class DemoOption(
    val id: Int,
    val label: String,
    val isBlocked: Boolean = false,
    val isPopular: Boolean = false,
)

val DropdownMenu by story {
    ControlResellTheme {
        val maxSelection by parameter(2)
        val enableSearch by parameter(false)
        val forceAtLeastOneSelection by parameter(true)
        val enableExclude by parameter(true)

        val options = remember {
            listOf(
                DemoOption(id = 1, label = "Option 1"),
                DemoOption(id = 2, label = "Option 2", isPopular = true),
                DemoOption(id = 3, label = "Option 3", isBlocked = true),
                DemoOption(id = 4, label = "Option 4"),
            )
        }

        var expanded by remember { mutableStateOf(false) }
        var selectionState by remember {
            mutableStateOf(
                SelectionState(
                    selected = listOf(options[0]),
                    excluded = emptyList()
                )
            )
        }
        var searchQuery by remember { mutableStateOf("") }

        DropdownMenu(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            options = options,
            selectionState = selectionState,
            onSelectionChange = { newState ->
                selectionState = newState
                println("Selection changed - Selected: ${newState.selected.map { it.label }}, Excluded: ${newState.excluded.map { it.label }}")
            },
            itemLabel = { it.label },
            itemKey = { it.id },
            itemIsBlocked = { it.isBlocked },
            itemIsPopular = { it.isPopular },
            label = "Select an option",
            maxSelection = maxSelection,
            enableSearch = enableSearch,
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            forceAtLeastOneSelection = forceAtLeastOneSelection,
            closeOnSelectionWhenMaxOneSelection = true,
            enableExclude = enableExclude,
            onBlockedItemPressed = { blocked ->
                println("Blocked item pressed: ${blocked.label}")
            },
            getColorForSelection = { value, _ ->
                if (value.isPopular) Color.Green else Color.White
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}

val ActivityIndicator by story {
    ControlResellTheme {
        val size by parameter(40)
        val segmentCount by parameter(8)
        val animationDuration by parameter(1000)

        ActivityIndicator(
            size = size.dp,
            style = DefaultActivityIndicatorStyle.copy(
                segmentCount = segmentCount,
                animationDuration = animationDuration
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
