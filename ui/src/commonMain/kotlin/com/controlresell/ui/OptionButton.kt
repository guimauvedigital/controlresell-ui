package com.controlresell.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OptionButton(
    text: String? = null,
    label: String? = null,
    icon: ImageVector? = null,
    onClick: (() -> Unit)? = null,
    style: OptionButtonStyle = LocalOptionButtonStyle.current,
    modifier: Modifier = Modifier,
    bubbleModifier: Modifier = Modifier,
    beforeText: (@Composable (Color) -> Unit)? = null,
    afterText: (@Composable (Color) -> Unit)? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    badgeCount: Int? = null,
    iconLabel: (@Composable (Modifier) -> Unit)? = null,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val underlayAlpha by animateFloatAsState(
        targetValue = if (pressed) 0.1f else 0f,
        animationSpec = tween(durationMillis = if (pressed) 120 else 240),
        label = "underlayAnim"
    )

    val baseColor = if (enabled) style.backgroundColor else Gray
    val baseTextColor = if (enabled) style.textColor else LocalColorScheme.current.onBackgroundVariant

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = bubbleModifier
                .clip(CircleShape)
                .background(baseColor)
                .border(2.dp, style.borderColor, CircleShape)
                .clickable(
                    enabled = enabled && !loading,
                    indication = null,
                    interactionSource = interactionSource
                ) { onClick?.invoke() }
                .padding(horizontal = if (text != null) 16.dp else 12.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (loading) {
                    ActivityIndicator(size = style.iconSize)
                } else {
                    (icon ?: style.defaultIcon)?.let {
                        Icon(
                            it,
                            contentDescription = null,
                            tint = style.textColor,
                            modifier = Modifier.height(style.iconSize)
                        )
                    }
                }

                beforeText?.invoke(baseTextColor)

                if (text != null) Text(
                    text = text,
                    color = baseTextColor,
                    style = style.textStyle,
                    modifier = Modifier.padding(vertical = 2.dp),
                )

                afterText?.invoke(baseTextColor)
            }

            // Underlay overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.White.copy(alpha = underlayAlpha))
            )
        }

        if (label != null) Text(
            text = label,
            modifier = Modifier.padding(top = 6.dp),
            style = style.textStyle,
            textAlign = TextAlign.Center
        )

        if (iconLabel != null) iconLabel(Modifier.padding(top = 6.dp))

        if (badgeCount != null && badgeCount > 0) Box(
            modifier = Modifier
                .offset(x = 12.dp, y = (-12).dp)
                .size(20.dp)
                .clip(CircleShape)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = badgeCount.toString(),
                style = style.textStyle,
                color = White
            )
        }
    }

}
