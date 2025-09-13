package com.controlresell.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.regular.CheckCircle
import com.adamglin.phosphoricons.regular.Info
import com.adamglin.phosphoricons.regular.Warning
import com.adamglin.phosphoricons.regular.WarningCircle

@Immutable
data class BannerStyle(
    val color: Color = Color.Unspecified,
    val icon: ImageVector? = null,
    val iconSize: Dp = 24.dp,
)

val WarningBannerStyle = BannerStyle(
    color = PrincetonOrange,
    icon = PhosphorIcons.Regular.WarningCircle,
)

val ErrorBannerStyle = BannerStyle(
    color = TerraCotta,
    icon = PhosphorIcons.Regular.Warning,
)

val SuccessBannerStyle = BannerStyle(
    color = Inchworm,
    icon = PhosphorIcons.Regular.CheckCircle,
)

val InfoBannerStyle = BannerStyle(
    color = RoyalBlue,
    icon = PhosphorIcons.Regular.Info,
)
