package com.controlresell.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font

val ClashDisplay
    @Composable get() = FontFamily(
        Font(
            resource = Res.font.ClashDisplay_Bold,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.ClashDisplay_Semibold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resource = Res.font.ClashDisplay_Medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.ClashDisplay_Regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.ClashDisplay_Light,
            weight = FontWeight.Light
        ),
        Font(
            resource = Res.font.ClashDisplay_Extralight,
            weight = FontWeight.ExtraLight
        ),
    )

val Inter
    @Composable get() = FontFamily(
        Font(
            resource = Res.font.Inter_Black,
            weight = FontWeight.Black
        ),
        Font(
            resource = Res.font.Inter_Bold,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.Inter_Semibold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resource = Res.font.Inter_Medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.Inter_Regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.Inter_Light,
            weight = FontWeight.Light
        ),
    )

@Immutable
data class ControlResellTypography(
    val h1: TextStyle = TextStyle.Default,
    val h2: TextStyle = TextStyle.Default,
    val h3: TextStyle = TextStyle.Default,
    val h5: TextStyle = TextStyle.Default,
    val h6: TextStyle = TextStyle.Default,
    val h6Bold: TextStyle = TextStyle.Default,
    val h6Semibold: TextStyle = TextStyle.Default,
    val p: TextStyle = TextStyle.Default,
    val label: TextStyle = TextStyle.Default,
    val labelSmall: TextStyle = TextStyle.Default,
    val labelVerySmall: TextStyle = TextStyle.Default,
)

val Typography: ControlResellTypography
    @Composable get() = ControlResellTypography(
        h1 = TextStyle(
            fontFamily = ClashDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 44.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        h2 = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        h3 = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        h5 = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        h6 = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        h6Bold = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        h6Semibold = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        p = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = LocalColorScheme.current.onBackground,
        ),
        label = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = LocalColorScheme.current.onBackgroundVariant,
        ),
        labelSmall = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = LocalColorScheme.current.onBackgroundVariant,
        ),
        labelVerySmall = TextStyle(
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = LocalColorScheme.current.onBackgroundVariant,
        ),
    )
