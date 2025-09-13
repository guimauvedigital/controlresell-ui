package com.controlresell.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.Check

@Immutable
data class OptionButtonStyle(
    val backgroundColor: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val textStyle: TextStyle = TextStyle.Default,
    val iconSize: Dp = 20.dp,
    val defaultIcon: ImageVector? = null,
)

val DefaultOptionButtonStyle
    @Composable get() = OptionButtonStyle(
        backgroundColor = Color(0x3AFFFFFF),
        textColor = Color.White,
        borderColor = Color.Transparent,
        textStyle = LocalTypography.current.p.copy(fontWeight = FontWeight.Bold),
        iconSize = 20.dp,
    )

val SelectedOptionButtonStyle
    @Composable get() = OptionButtonStyle(
        backgroundColor = InchwormOpacity12,
        textColor = Inchworm,
        borderColor = Inchworm,
        textStyle = LocalTypography.current.p.copy(fontWeight = FontWeight.Bold),
        defaultIcon = PhosphorIcons.Bold.Check,
        iconSize = 20.dp,
    )
