package com.controlresell.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.fill.XCircle
import com.controlresell.ui.generated.resources.Res
import com.controlresell.ui.generated.resources.clear_button_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    style: InputStyle = LocalInputStyle.current,
    label: String? = null,
    placeholder: String? = null,
    inputStringSuffix: String? = null,
    onFocus: (() -> Unit)? = null,
    onBlur: (() -> Unit)? = null,
    onClear: (() -> Unit)? = null,
    endElement: (@Composable (() -> Unit))? = null,
    errorMessage: String? = null,
    successMessage: String? = null,
    shouldFocus: Boolean = false,
    multiline: Boolean = false,
) {

    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = when {
            errorMessage != null -> LocalColorScheme.current.error
            isFocused -> style.focusBorderColor
            else -> style.borderColor
        },
        animationSpec = tween(durationMillis = 350),
        label = "borderColorAnim"
    )

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(shouldFocus) {
        if (shouldFocus) focusRequester.requestFocus()
    }

    Column(modifier) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, borderColor, RoundedCornerShape(8.dp))
                .background(style.backgroundColor)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .onFocusChanged { state ->
                    if (state.isFocused && !isFocused) {
                        isFocused = true
                        onFocus?.invoke()
                    } else if (!state.isFocused && isFocused) {
                        isFocused = false
                        onBlur?.invoke()
                    }
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    if (label != null) Text(
                        text = label,
                        style = style.labelTextStyle,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BasicTextField(
                            value = value,
                            onValueChange = onValueChange,
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester),
                            textStyle = style.textStyle,
                            singleLine = !multiline,
                            decorationBox = { innerTextField ->
                                if (value.isEmpty() && placeholder != null) Text(
                                    text = placeholder,
                                    style = style.placeholderTextStyle,
                                )
                                innerTextField()
                            }
                        )

                        if (inputStringSuffix != null) Text(
                            text = inputStringSuffix,
                            style = style.textStyle,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                        if (onClear != null && value.isNotEmpty()) Icon(
                            imageVector = PhosphorIcons.Fill.XCircle,
                            contentDescription = stringResource(Res.string.clear_button_description),
                            tint = style.textStyle.color,
                            modifier = Modifier.clickable { onClear() }
                        )
                    }
                }

                if (endElement != null) endElement()
            }
        }

        if (errorMessage != null) Text(
            text = errorMessage,
            color = LocalColorScheme.current.error,
            modifier = Modifier.padding(top = 4.dp)
        )

        if (successMessage != null) Text(
            text = successMessage,
            color = LocalColorScheme.current.success,
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}
