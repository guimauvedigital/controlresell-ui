package com.controlresell.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Scaffold(
    bottomBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(PaddingValues) -> Unit,
) {

    var bottomBarHeight by remember { mutableStateOf(0) }

    Box(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            content(
                PaddingValues(
                    bottom = with(LocalDensity.current) { bottomBarHeight.toDp() }
                )
            )
        }
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .onSizeChanged { bottomBarHeight = it.height }
        ) {
            bottomBar()
        }
    }

}
