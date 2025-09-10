package com.controlresell.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class AppColorScheme(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val success: Color,
    val warning: Color,
    val error: Color,
)

val DarkAppColorScheme = AppColorScheme(
    primary = AppColors.Primary,
    secondary = AppColors.Secondary,
    background = AppColors.ChineseBlack,
    surface = AppColors.CharlestonGreen,
    textPrimary = AppColors.White,
    textSecondary = AppColors.PhilippineSilver,
    success = AppColors.Success,
    warning = AppColors.Warning.W300,
    error = AppColors.Error,
)

@Immutable
data class AppTypography(
    val h1: TextStyle,
    val body: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
)

val DefaultTypography = AppTypography(
    h1 = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
    body = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    button = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
    caption = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light),
)


val LocalAppColors = staticCompositionLocalOf<AppColorScheme> { DarkAppColorScheme }
val LocalAppTypography = staticCompositionLocalOf<AppTypography> { DefaultTypography }
val LocalDefaultTextStyle = staticCompositionLocalOf { TextStyle.Default }

@Composable
fun AppTheme(
    typography: AppTypography = DefaultTypography,
    content: @Composable () -> Unit,
) {
    val colors = DarkAppColorScheme

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalDefaultTextStyle provides LocalAppTypography.current.body.copy(color = LocalAppColors.current.textPrimary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            content()
        }
    }
}
