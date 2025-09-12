package com.controlresell.ui.components.base

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.LocalAppColors
import com.controlresell.ui.theme.LocalAppTypography

@Composable
fun Badge(
    color: Color,
    text: String,
    desc: String? = null,
    deletable: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalAppTypography.current.body,
    blocked: Boolean = false,
) {
    Row(
        modifier = modifier
            .background(LocalAppColors.current.surface, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = if (desc != null) 6.dp else 4.dp)
            .heightIn(min = if (desc != null) 44.dp else 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Leading dot (equivalent to SquareDot)
        Canvas(modifier = Modifier.size(12.dp)) {
            drawCircle(color = color)
        }

        if (blocked) {
            // Blurred placeholder (like your Image)
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .width(60.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(LocalAppColors.current.surface.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "...",
                    style = textStyle.copy(color = LocalAppColors.current.textPrimary)
                )
            }
        } else {
            Column(modifier = Modifier.weight(1f, fill = false)) {
                Text(
                    text = text,
                    style = textStyle.copy(
                        color = if (desc != null) LocalAppColors.current.textPrimary else LocalAppColors.current.textPrimary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (desc != null) {
                    Text(
                        text = desc,
                        style = textStyle.copy(color = LocalAppColors.current.textPrimary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        if (deletable != null) {
            IconButton(
                onClick = { if (!blocked) deletable() },
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete badge",
                    tint = LocalAppColors.current.textPrimary
                )
            }
        }
    }
}
