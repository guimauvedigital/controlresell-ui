package com.controlresell.ui.components.inputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.controlresell.ui.components.base.AppText
import com.controlresell.ui.theme.AppColors
import com.controlresell.ui.theme.LocalAppColors
import com.controlresell.ui.theme.LocalAppTypography

@Composable
fun AppInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    inputStringSuffix: String? = null,
    onFocus: (() -> Unit)? = null,
    onBlur: (() -> Unit)? = null,
    onClear: (() -> Unit)? = null,
    rightElement: (@Composable (() -> Unit))? = null,
    errorMsg: String? = null,
    successMsg: String? = null,
    shouldFocus: Boolean = false,
    multiline: Boolean = false,
    focusBorderColor: Color = AppColors.DavysGray,
) {

    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = when {
            errorMsg != null -> LocalAppColors.current.error
            isFocused -> focusBorderColor
            else -> AppColors.CharlestonGreen
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
                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                .background(AppColors.CharlestonGreen)
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    if (label != null) {
                        AppText(
                            text = label,
                            style = LocalAppTypography.current.body,
                            color = LocalAppColors.current.textSecondary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BasicTextField(
                            value = value,
                            onValueChange = onValueChange,
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester),
                            textStyle = LocalAppTypography.current.body.copy(
                                color = LocalAppColors.current.textPrimary
                            ),
                            singleLine = !multiline,
                            decorationBox = { innerTextField ->
                                if (value.isEmpty() && placeholder != null) {
                                    AppText(
                                        text = placeholder,
                                        color = LocalAppColors.current.textSecondary
                                    )
                                }
                                innerTextField()
                            }
                        )

                        if (inputStringSuffix != null) {
                            AppText(
                                text = inputStringSuffix,
                                color = LocalAppColors.current.textPrimary,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                        if (onClear != null && value.isNotEmpty()) {
                            IconButton(onClick = { onClear() }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear",
                                    tint = LocalAppColors.current.textPrimary
                                )
                            }
                        }
                    }
                }

                if (rightElement != null) {
                    rightElement()
                }
            }
        }

        if (errorMsg != null) {
            AppText(
                text = errorMsg,
                style = LocalAppTypography.current.body,
                color = LocalAppColors.current.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (successMsg != null) {
            AppText(
                text = successMsg,
                style = LocalAppTypography.current.body,
                color = AppColors.Inchworm,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
