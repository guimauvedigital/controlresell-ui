package com.controlresell.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.NavigationBarItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: NavigationBarStyle = LocalNavigationBarStyle.current,
    renderIcon: @Composable (ImageVector, Dp, Color) -> Unit = { imageVector, size, color ->
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(size)
        )
    },
) {

    val color = if (selected) style.selectedTextColor else style.textColor

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val alpha by animateFloatAsState(
        targetValue = if (pressed) 0.75f else 1f,
        animationSpec = tween(durationMillis = if (pressed) 100 else 150)
    )

    Column(
        modifier = modifier
            .weight(1f)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = interactionSource
            )
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        renderIcon(icon, style.iconSize, color.copy(alpha = alpha))
        Text(
            text = label,
            style = LocalTypography.current.labelSmall.copy(color = color.copy(alpha = alpha)),
            modifier = Modifier.padding(top = 0.dp)
        )
    }

}
