package com.controlresell.ui.components.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.controlresell.ui.components.base.AppText
import com.controlresell.ui.theme.AppColors
import com.controlresell.ui.theme.LocalAppColors
import com.controlresell.ui.theme.LocalAppTypography

@Composable
fun OptionButton(
    text: String? = null,
    label: String? = null,
    icon: (@Composable (() -> Unit))? = null,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    beforeText: (@Composable (Color) -> Unit)? = null,
    afterText: (@Composable (Color) -> Unit)? = null,
    backgroundColor: Color = Color(0x3AFFFFFF), // rgba(255,255,255,0.23)
    textColor: Color = AppColors.White,
    borderColor: Color = Color.Transparent,
    enabled: Boolean = true,
    bubbleStyle: Modifier = Modifier,
    textStyle: TextStyle = LocalAppTypography.current.body.copy(fontWeight = FontWeight.Medium),
    loading: Boolean = false,
    badgeCount: Int? = null,
    iconLabel: (@Composable (Modifier) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val underlayAlpha by animateFloatAsState(
        targetValue = if (pressed) 0.1f else 0f,
        animationSpec = tween(durationMillis = if (pressed) 120 else 240),
        label = "underlayAnim"
    )

    val baseColor = if (enabled) backgroundColor else AppColors.Gray
    val baseTextColor = if (enabled) textColor else LocalAppColors.current.textSecondary

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = bubbleStyle
                .clip(CircleShape)
                .background(baseColor)
                .border(1.dp, if (borderColor != Color.Transparent) borderColor else baseColor, CircleShape)
                .clickable(
                    enabled = enabled && !loading,
                    indication = null,
                    interactionSource = interactionSource
                ) { onClick?.invoke() }
                .padding(horizontal = if (text != null) 16.dp else 12.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp),
                        color = AppColors.White
                    )
                } else {
                    icon?.invoke()
                }

                beforeText?.invoke(baseTextColor)

                if (text != null) {
                    AppText(
                        text = text,
                        color = baseTextColor,
                        style = textStyle
                    )
                }

                afterText?.invoke(baseTextColor)
            }

            // Underlay overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.White.copy(alpha = underlayAlpha))
            )
        }

        if (label != null) {
            AppText(
                text = label,
                modifier = Modifier.padding(top = 6.dp),
                style = LocalAppTypography.current.body.copy(fontWeight = FontWeight.Medium),
                color = LocalAppColors.current.textPrimary,
                textAlign = TextAlign.Center
            )
        }

        if (iconLabel != null) {
            iconLabel(Modifier.padding(top = 6.dp))
        }

        if (badgeCount != null && badgeCount > 0) {
            Box(
                modifier = Modifier
                    .offset(x = 12.dp, y = (-12).dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                AppText(
                    text = badgeCount.toString(),
                    style = LocalAppTypography.current.body.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    color = AppColors.White
                )
            }
        }
    }
}
