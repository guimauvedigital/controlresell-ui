package com.controlresell.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.Check
import com.controlresell.ui.generated.resources.Res
import com.controlresell.ui.generated.resources.checkbox_checked
import org.jetbrains.compose.resources.stringResource

enum class CheckboxShape { Circle, Square }

@Composable
fun Checkbox(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: CheckboxShape = CheckboxShape.Circle,
    label: (@Composable (TextStyle) -> Unit)? = null,
) {

    val borderColor = if (value) LocalColorScheme.current.primary else Color.White.copy(alpha = 0.2f)
    val backgroundColor = if (value) LocalColorScheme.current.primary else Color.Transparent
    val cornerRadius = if (shape == CheckboxShape.Square) 4.dp else 100.dp
    val alpha = if (enabled) 1f else 0.4f

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .alpha(alpha)
            .clickable(
                enabled = enabled,
                onClick = { onValueChange(!value) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = if (shape == CheckboxShape.Square) RoundedCornerShape(cornerRadius) else CircleShape
                )
                .then(
                    Modifier
                        .padding(2.dp)
                        .background(
                            color = Color.Transparent,
                            shape = if (shape == CheckboxShape.Square) RoundedCornerShape(cornerRadius) else CircleShape
                        )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (value) Box(
                modifier = Modifier
                    .size(if (shape == CheckboxShape.Square) 20.dp else 10.dp)
                    .background(
                        color = backgroundColor,
                        shape = if (shape == CheckboxShape.Square) RectangleShape else CircleShape
                    )
            ) {
                if (shape == CheckboxShape.Square) Icon(
                    imageVector = PhosphorIcons.Bold.Check,
                    contentDescription = stringResource(Res.string.checkbox_checked),
                    tint = LocalColorScheme.current.background,
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        if (label != null) {
            Spacer(Modifier.width(8.dp))
            label(
                TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
        }
    }
}
