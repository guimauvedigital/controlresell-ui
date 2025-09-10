package com.controlresell.ui.components.inputs

import org.jetbrains.compose.storytale.story

val `AppInput default state` by story {
    val value by parameter("Input text")
    val placeholder by parameter("Hint text")
    val label by parameter<String?>(null)
    val successMsg by parameter<String?>(null)
    val errorMsg by parameter<String?>(null)

    AppInput(
        value = value,
        onValueChange = {},
        placeholder = placeholder,
        label = label,
        successMsg = successMsg,
        errorMsg = errorMsg,
    )
}
