package com.controlresell.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Banner(
    style: BannerStyle,
    description: String,
    title: String? = null,
    modifier: Modifier = Modifier,
) {

    val backgroundColor = style.color.copy(alpha = 0.125f)

    Row(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        style.icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = style.color,
                modifier = Modifier.size(style.iconSize)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            if (title != null) {
                Text(
                    text = title,
                    style = LocalTypography.current.h6Semibold
                )
                Spacer(Modifier.height(4.dp))
            }
            Text(
                text = description,
                style = LocalTypography.current.label,
            )
        }
    }
}
