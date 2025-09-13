package com.controlresell.ui.components

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.ButtonStyle
import com.controlresell.ui.theme.Gray
import com.controlresell.ui.theme.LocalButtonStyle
import com.controlresell.ui.theme.LocalTypography

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

    var pressed by remember { mutableStateOf(false) }
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
        overridingContent?.invoke() ?: Text(
            text ?: "",
            color = style.textColor,
            style = LocalTypography.current.p
        )

        if (loading) CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            color = Gray,
            strokeWidth = 2.dp
        )
    }

}
