package com.controlresell.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun NavigationHeader(
    title: String? = null,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = LocalTypography.current.h5,
    startElement: (@Composable () -> Unit)? = null,
    endElement: (@Composable () -> Unit)? = null,
    onLayout: ((IntSize) -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.safeContent.only(WindowInsetsSides.Top))
            .padding(vertical = 8.dp, horizontal = 16.dp)
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
                startElement?.invoke()
            }

            // If children are provided → use them, otherwise show title
            Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.Center) {
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
                endElement?.invoke()
            }
        }
    }
}
