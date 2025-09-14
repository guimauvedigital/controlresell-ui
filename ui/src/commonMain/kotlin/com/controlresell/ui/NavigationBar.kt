package com.controlresell.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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
        Box {
            Box(
                Modifier
                    .matchParentSize()
                    .background(style.backgroundColor)
                    .blur(style.blurRadius) // This does not apply to content under the bar :(
                    .graphicsLayer {
                        // And this is not available on iOS yet :(
                        /*
                        renderEffect = RenderEffect.createBlurEffect(
                            style.blurRadius.toPx(), style.blurRadius.toPx(),
                            Shader.TileMode.CLAMP
                        )
                         */
                    }
            )
            Row(
                modifier = modifier
                    .windowInsetsPadding(
                        WindowInsets.safeContent
                            .exclude(WindowInsets.ime)
                            .only(WindowInsetsSides.Bottom)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
                content = content
            )
        }
    }
}
