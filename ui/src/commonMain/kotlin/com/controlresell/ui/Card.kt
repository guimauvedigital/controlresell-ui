package com.controlresell.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun Card(
    modifier: Modifier = Modifier,
    style: CardStyle = LocalCardStyle.current,
    skeleton: SkeletonPlaceholder = SkeletonPlaceholder(),
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLayout: ((IntSize) -> Unit)? = null,
    content: @Composable () -> Unit,
) {

    val density = LocalDensity.current

    var layoutSize by remember { mutableStateOf(IntSize.Zero) }
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    // Press animation: fade opacity when pressed
    val alpha by animateFloatAsState(
        targetValue = if (pressed) 0.75f else 1f,
        animationSpec = tween(durationMillis = if (pressed) 100 else 150)
    )

    Box(
        modifier = modifier
            .alpha(alpha)
            .background(
                style.backgroundColor, RoundedCornerShape(
                    topStart = style.roundedCorners.topStart,
                    topEnd = style.roundedCorners.topEnd,
                    bottomStart = style.roundedCorners.bottomStart,
                    bottomEnd = style.roundedCorners.bottomEnd
                )
            )
            .then(
                Modifier.borderSides(
                    width = style.borderWidth,
                    color = style.borderColor,
                    visibility = style.borderVisibility
                )
            )
            .onGloballyPositioned { coords ->
                val size = coords.size
                val intSize = IntSize(size.width, size.height)
                if (intSize != layoutSize) {
                    layoutSize = intSize
                    onLayout?.invoke(intSize)
                }
            }
            .then(
                if (enabled && (onClick != null || onLongClick != null)) {
                    Modifier.combinedClickable(
                        interactionSource = interactionSource,
                        indication = null, // we use opacity animation instead
                        onClick = { onClick?.invoke() },
                        onLongClick = { onLongClick?.invoke() }
                    )
                } else Modifier
            )
            .padding(style.paddingValues)
    ) {
        if (skeleton.visible) Box(
            modifier = Modifier
                .then(
                    if (skeleton.renderHiddenContent) Modifier.matchParentSize()
                    else Modifier
                )
        ) {
            // Replace with your actual skeleton Composable
            Box(
                modifier = Modifier
                    .then(
                        with(density) {
                            Modifier
                                .width(skeleton.width ?: layoutSize.width.toDp())
                                .height(skeleton.height ?: layoutSize.height.toDp())
                        }
                    )
                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            )
        }

        if (!skeleton.visible || skeleton.renderHiddenContent) Box(
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

}
