package com.controlresell.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.AppColors
import org.jetbrains.compose.storytale.story

val `OptionButton default state` by story {
    val text by parameter("Option Button")
    val enabled by parameter(true)

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OptionButton(
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = if (enabled) AppColors.White else AppColors.Gray,
                )
            },
            enabled = enabled,
            onClick = {},
        )

        OptionButton(
            text = text,
            enabled = enabled,
            onClick = {},
        )

        OptionButton(
            text = text,
            enabled = enabled,
            onClick = {},
            textColor = AppColors.Inchworm,
            borderColor = AppColors.Inchworm,
            backgroundColor = AppColors.InchwormOpacity12,
            icon = {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = AppColors.Inchworm,
                )
            }
        )
    }
}
