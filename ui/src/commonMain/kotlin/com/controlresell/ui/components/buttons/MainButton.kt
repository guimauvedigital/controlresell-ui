package com.controlresell.ui.components.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.LocalAppTypography
import com.controlresell.ui.theme.buttons.ButtonPalette
import com.controlresell.ui.theme.buttons.ButtonPalettes

@Composable
fun MainButton(
    text: String? = null,
    type: ButtonPalette = ButtonPalettes.Primary,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    overridingContent: (@Composable (() -> Unit))? = null,
    onClick: () -> Unit,
) {
    // Press animation (background color)
    var pressed by remember { mutableStateOf(false) }
    val bgColor by animateColorAsState(
        targetValue = if (pressed) type.underlay else type.background,
        animationSpec = tween(durationMillis = 150)
    )

    // Disabled animation (opacity)
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
                onClickLabel = text
            )
            /*
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> pressed = true
                    MotionEvent.ACTION_UP,
                    MotionEvent.ACTION_CANCEL -> pressed = false
                }
                false
            }
             */
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Color.Gray,
                strokeWidth = 2.dp
            )
        } else {
            overridingContent?.invoke() ?: Text(
                text ?: "",
                color = type.textColor,
                style = LocalAppTypography.current.button
            )
        }
    }
}
