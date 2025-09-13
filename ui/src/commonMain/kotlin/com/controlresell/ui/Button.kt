package com.controlresell.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    text: String? = null,
    modifier: Modifier = Modifier,
    style: ButtonStyle = LocalButtonStyle.current,
    enabled: Boolean = true,
    loading: Boolean = false,
    overridingContent: (@Composable (() -> Unit))? = null,
    onClick: () -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val bgColor by animateColorAsState(
        targetValue = if (pressed) style.underlayColor else style.backgroundColor,
        animationSpec = tween(durationMillis = 150)
    )

    val opacity by animateFloatAsState(
        targetValue = if (!enabled || loading) 0.5f else 1f,
        animationSpec = tween(durationMillis = 200)
    )

    Box(
        modifier = modifier
            .alpha(opacity)
            .clip(CircleShape)
            .background(bgColor)
            .clickable(
                enabled = enabled && !loading,
                onClick = onClick,
                onClickLabel = text,
                interactionSource = interactionSource,
                indication = null, // we use opacity animation instead
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        overridingContent?.invoke() ?: Text(
            text ?: "",
            color = style.textColor,
            style = LocalTypography.current.p
        )

        if (loading) ActivityIndicator(size = 20.dp)
    }

}
