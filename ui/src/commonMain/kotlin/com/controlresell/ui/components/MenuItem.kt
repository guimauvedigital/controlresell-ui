package com.controlresell.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.CharlestonGreen
import com.controlresell.ui.theme.Jet
import com.controlresell.ui.theme.LocalColorScheme
import com.controlresell.ui.theme.LocalTypography

data class BorderRadiusPerSide(
    val top: Boolean = true,
    val bottom: Boolean = true,
    val left: Boolean = true,
    val right: Boolean = true,
) {
    companion object {
        val full = BorderRadiusPerSide()
        val topFull = BorderRadiusPerSide(top = true, bottom = false)
        val bottomFull = BorderRadiusPerSide(top = false, bottom = true)
    }
}

data class BorderPerSide(
    val top: Boolean = true,
    val bottom: Boolean = true,
    val left: Boolean = true,
    val right: Boolean = true,
) {
    companion object {
        val full = BorderPerSide()
        val topFull = BorderPerSide(top = true, bottom = false)
        val bottomFull = BorderPerSide(top = false, bottom = true)
    }
}

@Composable
fun MenuItem(
    label: String? = null,
    labelStyle: TextStyle? = null,
    label2: Pair<String, TextStyle?>? = null,
    actionLabel: String? = null,
    actionLabelStyle: TextStyle? = null,
    actionLabelColor: Color? = null,
    actionIconHidden: Boolean = false,
    customActionElement: (@Composable (actionLabelElement: @Composable () -> Unit) -> Unit)? = null,
    leftElement: (@Composable (() -> Unit))? = null,
    children: (@Composable (() -> Unit))? = null,
    cardModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    hasBorderRadiusPerSide: BorderRadiusPerSide = BorderRadiusPerSide.full,
    hideBorderPerSide: BorderPerSide = BorderPerSide.full,
    onClick: (() -> Unit)? = null,
    disabled: Boolean = false,
    overridingContent: (@Composable (() -> Unit))? = null,
    backgroundColor: Color = CharlestonGreen,
    borderColor: Color = Jet,
) {
    val shape = RoundedCornerShape(
        topStart = if (hasBorderRadiusPerSide.top) 8.dp else 0.dp,
        topEnd = if (hasBorderRadiusPerSide.top) 8.dp else 0.dp,
        bottomStart = if (hasBorderRadiusPerSide.bottom) 8.dp else 0.dp,
        bottomEnd = if (hasBorderRadiusPerSide.bottom) 8.dp else 0.dp,
    )

    Box(
        modifier = cardModifier
            .fillMaxWidth()
            .clip(shape)
            .background(backgroundColor)
            .then(
                if (hideBorderPerSide.top || hideBorderPerSide.bottom || hideBorderPerSide.left || hideBorderPerSide.right) {
                    Modifier.drawWithContent {
                        drawContent()
                        val strokeWidth = 1.dp.toPx()
                        val halfStroke = strokeWidth / 2
                        val w = size.width
                        val h = size.height
                        if (!hideBorderPerSide.top) drawLine(
                            borderColor,
                            Offset(0f, halfStroke),
                            Offset(w, halfStroke),
                            strokeWidth
                        )
                        if (!hideBorderPerSide.bottom) drawLine(
                            borderColor,
                            Offset(0f, h - halfStroke),
                            Offset(w, h - halfStroke),
                            strokeWidth
                        )
                        if (!hideBorderPerSide.left) drawLine(
                            borderColor,
                            Offset(halfStroke, 0f),
                            Offset(halfStroke, h),
                            strokeWidth
                        )
                        if (!hideBorderPerSide.right) drawLine(
                            borderColor,
                            Offset(w - halfStroke, 0f),
                            Offset(w - halfStroke, h),
                            strokeWidth
                        )
                    }
                } else Modifier
            )
            .clickable(enabled = !disabled) { onClick?.invoke() }
    ) {
        if (overridingContent != null) {
            overridingContent()
        } else {
            Column(modifier = contentModifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        leftElement?.invoke()
                        Column {
                            if (label != null) {
                                com.controlresell.ui.components.Text(
                                    text = label,
                                    style = labelStyle ?: LocalTypography.current.p,
                                    modifier = if (leftElement != null) Modifier.padding(start = 16.dp) else Modifier
                                )
                            }
                            label2?.let { (text, style) ->
                                com.controlresell.ui.components.Text(
                                    text = text,
                                    style = style ?: LocalTypography.current.p,
                                    modifier = if (leftElement != null) Modifier.padding(start = 16.dp) else Modifier
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val actionElement: @Composable () -> Unit = {
                            actionLabel?.let {
                                Text(
                                    text = it,
                                    style = actionLabelStyle ?: LocalTypography.current.p,
                                    color = actionLabelColor ?: LocalColorScheme.current.onSurfaceVariant
                                )
                            }
                        }

                        if (customActionElement != null) {
                            customActionElement(actionElement)
                        } else {
                            actionElement()
                            if (!actionIconHidden) {
                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = "Next",
                                    tint = LocalColorScheme.current.onSurfaceVariant,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }

                children?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    it()
                }
            }
        }
    }
}
