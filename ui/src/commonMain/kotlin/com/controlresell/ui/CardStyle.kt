package com.controlresell.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class SkeletonPlaceholder(
    val visible: Boolean = false,
    val width: Dp? = null,
    val height: Dp? = null,
    val renderHiddenContent: Boolean = false,
)

val NoRadius = 0.dp
val RoundedRadius = 8.dp

@Immutable
data class RoundedCorners(
    val topStart: Dp = NoRadius,
    val topEnd: Dp = NoRadius,
    val bottomStart: Dp = NoRadius,
    val bottomEnd: Dp = NoRadius,
)

val NoRoundedCorners = RoundedCorners()
val FullRoundedCorners = RoundedCorners(RoundedRadius, RoundedRadius, RoundedRadius, RoundedRadius)
val TopRoundedCorners = RoundedCorners(RoundedRadius, RoundedRadius, NoRadius, NoRadius)
val BottomRoundedCorners = RoundedCorners(NoRadius, NoRadius, RoundedRadius, RoundedRadius)
val StartRoundedCorners = RoundedCorners(RoundedRadius, NoRadius, RoundedRadius, NoRadius)
val EndRoundedCorners = RoundedCorners(NoRadius, RoundedRadius, NoRadius, RoundedRadius)

@Immutable
data class BorderVisibility(
    val top: Boolean = false,
    val bottom: Boolean = false,
    val start: Boolean = false,
    val end: Boolean = false,
)

val NoBorders = BorderVisibility()
val FullBorders = BorderVisibility(top = true, bottom = true, start = true, end = true)
val TopBorders = BorderVisibility(top = true)
val BottomBorders = BorderVisibility(bottom = true)
val StartBorders = BorderVisibility(start = true)
val EndBorders = BorderVisibility(end = true)

fun Modifier.borderSides(
    width: Dp,
    color: Color,
    visibility: BorderVisibility = FullBorders,
): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = width.toPx()
        if (strokeWidth <= 0f) return@drawBehind

        val half = strokeWidth / 2f
        val w = size.width
        val h = size.height

        if (visibility.top) drawLine(
            color = color,
            start = Offset(0f, half),
            end = Offset(w, half),
            strokeWidth = strokeWidth
        )
        if (visibility.bottom) drawLine(
            color = color,
            start = Offset(0f, h - half),
            end = Offset(w, h - half),
            strokeWidth = strokeWidth
        )
        if (visibility.start) drawLine(
            color = color,
            start = Offset(half, 0f),
            end = Offset(half, h),
            strokeWidth = strokeWidth
        )
        if (visibility.end) drawLine(
            color = color,
            start = Offset(w - half, 0f),
            end = Offset(w - half, h),
            strokeWidth = strokeWidth
        )
    }
)

@Immutable
data class CardStyle(
    val backgroundColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val borderWidth: Dp = 0.dp,
    val roundedCorners: RoundedCorners = RoundedCorners(),
    val borderVisibility: BorderVisibility = BorderVisibility(),
    val paddingValues: PaddingValues = PaddingValues(),
)

val DefaultCardStyle
    @Composable get() = CardStyle(
        backgroundColor = LocalColorScheme.current.surface,
        borderColor = Jet,
        borderWidth = 0.dp,
        roundedCorners = FullRoundedCorners,
        borderVisibility = NoBorders,
        paddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    )

val BorderedCardStyle
    @Composable get() = CardStyle(
        backgroundColor = LocalColorScheme.current.surface,
        borderColor = Jet,
        borderWidth = 2.dp,
        roundedCorners = NoRoundedCorners,
        borderVisibility = FullBorders,
        paddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    )
