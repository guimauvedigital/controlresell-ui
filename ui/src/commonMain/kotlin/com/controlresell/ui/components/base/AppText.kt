package com.controlresell.ui.components.base

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.controlresell.ui.theme.LocalAppColors
import com.controlresell.ui.theme.LocalAppTypography

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalAppTypography.current.body,
    color: Color = LocalAppColors.current.textPrimary,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color
    )
}
