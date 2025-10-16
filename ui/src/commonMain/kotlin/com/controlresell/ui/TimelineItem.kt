package com.controlresell.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.CaretRight

@Composable
fun TimelineItem(
    title: String,
    modifier: Modifier = Modifier,
    leftText: String? = null,
    subtitle: String? = null,
    dotColor: Color? = null,
    showLine: Boolean = true,
    showChevron: Boolean = true,
    onClick: (() -> Unit)? = null,
    style: TimelineItemStyle = DefaultTimelineItemStyle,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val alpha by animateFloatAsState(
        targetValue = if (pressed) 0.75f else 1f,
        animationSpec = tween(durationMillis = if (pressed) 100 else 150)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ) else Modifier
            )
            .alpha(alpha),
        verticalAlignment = Alignment.Top
    ) {
        leftText?.let {
            Text(
                text = it,
                style = style.timeTextStyle,
                modifier = Modifier
                    .width(70.dp)
                    .padding(end = 12.dp)
            )
        }

        // Timeline indicator (dot and line)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(20.dp)
        ) {
            // Dot
            Box(
                modifier = Modifier
                    .size(style.dotSize)
                    .background(
                        color = dotColor ?: style.dotColor,
                        shape = CircleShape
                    )
            )

            // Vertical line
            if (showLine) {
                Box(
                    modifier = Modifier
                        .width(style.lineWidth)
                        .height(46.dp)
                        .background(color = style.lineColor)
                )
            }
        }

        // Content (title and subtitle)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = title,
                style = style.titleTextStyle
            )

            if (subtitle != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = style.subtitleTextStyle
                )
            }
        }

        // Chevron
        if (showChevron) {
            Icon(
                imageVector = PhosphorIcons.Regular.CaretRight,
                contentDescription = null,
                tint = style.chevronColor,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
