package com.controlresell.ui.components.base

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.controlresell.ui.theme.LocalAppColors
import com.controlresell.ui.theme.LocalAppTypography

@Composable
fun CustomHeader(
    title: String? = null,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = LocalAppTypography.current.body.copy(
        color = LocalAppColors.current.textPrimary
    ),
    leftElement: (@Composable () -> Unit)? = null,
    rightElement: (@Composable () -> Unit)? = null,
    onLayout: ((IntSize) -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .onGloballyPositioned { coords ->
                onLayout?.invoke(coords.size)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                leftElement?.invoke()
            }

            // If children are provided → use them, otherwise show title
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                if (content != null) {
                    content()
                } else if (title != null) {
                    Text(
                        text = title,
                        style = titleStyle,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                rightElement?.invoke()
            }
        }
    }
}
