package com.controlresell.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Switch(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: (@Composable (TextStyle) -> Unit)? = null,
) {

    val width = 50.dp
    val height = 30.dp
    val knobSize = 26.dp
    val padding = 2.dp

    val trackColor by animateColorAsState(
        targetValue = if (value) LocalColorScheme.current.primary else Jet,
        label = "trackColor"
    )
    val knobOffset by animateDpAsState(
        targetValue = if (value) (width - knobSize - padding) else padding,
        label = "knobOffset"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(enabled = enabled) { onValueChange(!value) }
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(percent = 50))
                .background(trackColor),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset(x = knobOffset)
                    .size(knobSize)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }

        if (label != null) {
            Spacer(Modifier.width(8.dp))
            label(
                TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
        }
    }

}
