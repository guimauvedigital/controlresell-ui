package com.controlresell.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.CaretRight

@Composable
fun MenuItem(
    label: String? = null,
    label2: String? = null,
    actionLabel: String? = null,
    actionIconHidden: Boolean = false,
    customActionElement: (@Composable (actionLabelElement: @Composable () -> Unit) -> Unit)? = null,
    leftElement: (@Composable (() -> Unit))? = null,
    children: (@Composable (() -> Unit))? = null,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    style: MenuItemStyle = LocalMenuItemStyle.current,
    cardStyle: CardStyle = LocalCardStyle.current,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
) {

    // Note: `overridingContent` was removed because it's the same as putting it directly in a `Card`

    Card(
        modifier = modifier,
        style = cardStyle,
        enabled = enabled,
        onClick = onClick,
    ) {
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
                                style = style.labelStyle,
                                modifier = if (leftElement != null) Modifier.padding(start = 16.dp) else Modifier
                            )
                        }
                        label2?.let {
                            Text(
                                text = label2,
                                style = style.label2Style,
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
                                style = style.actionLabelStyle
                            )
                        }
                    }

                    if (customActionElement != null) {
                        customActionElement(actionElement)
                    } else {
                        actionElement()
                        if (!actionIconHidden) Icon(
                            imageVector = PhosphorIcons.Regular.CaretRight,
                            contentDescription = "Next",
                            tint = LocalColorScheme.current.onSurfaceVariant,
                            modifier = Modifier.size(24.dp)
                        )
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
