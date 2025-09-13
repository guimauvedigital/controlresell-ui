package com.controlresell.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.CardStyle
import com.controlresell.ui.theme.LocalCardStyle
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
    cardStyle: CardStyle = LocalCardStyle.current,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = false,
    overridingContent: (@Composable (() -> Unit))? = null,
) {

    Card(
        modifier = cardModifier,
        style = cardStyle,
        enabled = enabled,
        onClick = onClick,
    ) {
        if (overridingContent != null) {
            overridingContent()
        } else {
            Column(modifier = contentModifier) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        leftElement?.invoke()
                        Column {
                            if (label != null) {
                                Text(
                                    text = label,
                                    style = labelStyle ?: LocalTypography.current.p,
                                    modifier = if (leftElement != null) Modifier.padding(start = 16.dp) else Modifier
                                )
                            }
                            label2?.let { (text, style) ->
                                Text(
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
