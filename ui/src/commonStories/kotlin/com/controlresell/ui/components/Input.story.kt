package com.controlresell.ui.components

import com.controlresell.ui.theme.ControlResellTheme
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
        )
    }
}
