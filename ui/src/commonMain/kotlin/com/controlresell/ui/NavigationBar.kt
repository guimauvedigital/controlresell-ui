package com.controlresell.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    style: NavigationBarStyle = LocalNavigationBarStyle.current,
    content: @Composable RowScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalNavigationBarStyle provides style,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .graphicsLayer {
                    /*
                    renderEffect = RenderEffect.createBlurEffect(
                        style.blurRadius.toPx(), style.blurRadius.toPx(),
                        Shader.TileMode.CLAMP
                    )
                     */
                }
                .background(style.backgroundColor),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top,
            content = content
        )
    }
}
