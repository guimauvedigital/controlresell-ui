package com.controlresell.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.X
import com.controlresell.ui.generated.resources.Res
import com.controlresell.ui.generated.resources.badge_delete_button_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun Badge(
    color: Color,
    text: String,
    desc: String? = null,
    deletable: (() -> Unit)? = null,
    style: BadgeStyle = LocalBadgeStyle.current,
    modifier: Modifier = Modifier,
    blocked: Boolean = false,
) {

    Row(
        modifier = modifier
            .background(style.backgroundColor, style.shape)
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
                    .background(style.backgroundColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "...",
                    style = style.textStyle,
                )
            }
        } else {
            Column(modifier = Modifier.weight(1f, fill = false)) {
                Text(
                    text = text,
                    style = style.textStyle.copy(
                        color = if (desc != null) LocalColorScheme.current.onSurfaceVariant else LocalColorScheme.current.onSurface
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (desc != null) {
                    Text(
                        text = desc,
                        style = style.textStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        if (deletable != null) Icon(
            imageVector = PhosphorIcons.Regular.X,
            contentDescription = stringResource(Res.string.badge_delete_button_description),
            tint = style.textStyle.color,
            modifier = Modifier.clickable { if (!blocked) deletable() }.size(20.dp)
        )
    }

}
