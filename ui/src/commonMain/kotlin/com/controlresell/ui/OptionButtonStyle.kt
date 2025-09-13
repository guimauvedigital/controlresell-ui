package com.controlresell.ui

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
    val horizontalPadding: Dp = 10.dp,
    val verticalPadding: Dp = 10.dp,
    val defaultIcon: ImageVector? = null,
)

val DefaultOptionButtonStyle
    @Composable get() = OptionButtonStyle(
        backgroundColor = Color(0x3AFFFFFF),
        textColor = LocalColorScheme.current.onSurface,
        borderColor = Color.Transparent,
        textStyle = LocalTypography.current.p.copy(fontWeight = FontWeight.Bold),
        iconSize = 20.dp,
        horizontalPadding = 10.dp,
        verticalPadding = 10.dp,
    )

val SelectedOptionButtonStyle
    @Composable get() = OptionButtonStyle(
        backgroundColor = InchwormOpacity12,
        textColor = LocalColorScheme.current.primary,
        borderColor = LocalColorScheme.current.primary,
        textStyle = LocalTypography.current.p.copy(fontWeight = FontWeight.Bold),
        defaultIcon = PhosphorIcons.Bold.Check,
        iconSize = 20.dp,
        horizontalPadding = 10.dp,
        verticalPadding = 10.dp,
    )

val StatusOptionButtonStyle
    @Composable get() = OptionButtonStyle(
        backgroundColor = Color(0x3AFFFFFF),
        textColor = LocalColorScheme.current.onSurface,
        borderColor = Color.Transparent,
        textStyle = LocalTypography.current.p.copy(fontWeight = FontWeight.SemiBold),
        iconSize = 16.dp,
        horizontalPadding = 10.dp,
        verticalPadding = 2.dp,
    )

val DraftStatusOptionButtonStyle
    @Composable get() = StatusOptionButtonStyle.copy(
        backgroundColor = CssGray,
        textColor = Black,
    )

@Composable
fun PendingStatusOptionButtonStyle(glowing: Boolean = false) = StatusOptionButtonStyle.copy(
    backgroundColor = if (glowing) PendingBlue else Link.L900,
    textColor = if (glowing) White else PictonBlue,
)

@Composable
fun ProgressStatusOptionButtonStyle(glowing: Boolean = false) = StatusOptionButtonStyle.copy(
    backgroundColor = if (glowing) PrincetonOrange else Warning.W900,
    textColor = if (glowing) White else PrincetonOrange,
)

@Composable
fun DoneStatusOptionButtonStyle(glowing: Boolean = false) = StatusOptionButtonStyle.copy(
    backgroundColor = if (glowing) ForestGreen else RifleGreen,
    textColor = if (glowing) White else Inchworm,
)

@Composable
fun ErrorStatusOptionButtonStyle(glowing: Boolean = false) = StatusOptionButtonStyle.copy(
    backgroundColor = if (glowing) TerraCotta else BrownCoffee,
    textColor = if (glowing) White else TerraCotta,
)
