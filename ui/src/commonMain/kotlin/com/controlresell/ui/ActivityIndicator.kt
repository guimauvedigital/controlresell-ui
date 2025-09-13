package com.controlresell.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ActivityIndicator(
    size: Dp,
    modifier: Modifier = Modifier,
    style: ActivityIndicatorStyle = LocalActivityIndicatorStyle.current,
) {

    val infiniteTransition = rememberInfiniteTransition()
    val rotationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = style.segmentCount.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(style.animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.size(size)) {
        val radius = size.toPx() / 2f
        val segmentAngle = 360f / style.segmentCount
        val segmentLength = radius / 2f
        val center = Offset(radius, radius)

        for (i in 0 until style.segmentCount) {
            // Shift alpha based on "rotationProgress"
            val alpha = (((i - rotationProgress + style.segmentCount) % style.segmentCount) / style.segmentCount)
                .coerceIn(0.1f, 1f)

            val angleRad = toRadians((segmentAngle * i).toDouble())
            val start = Offset(
                x = center.x + (radius - segmentLength) * cos(angleRad).toFloat(),
                y = center.y + (radius - segmentLength) * sin(angleRad).toFloat()
            )
            val end = Offset(
                x = center.x + radius * cos(angleRad).toFloat(),
                y = center.y + radius * sin(angleRad).toFloat()
            )
            drawLine(
                color = style.color.copy(alpha = alpha),
                start = start,
                end = end,
                strokeWidth = radius / 4f,
                cap = StrokeCap.Round
            )
        }
    }

}

private fun toRadians(value: Double): Double = value * (PI / 180.0)
