package com.controlresell.ui.components.base

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.controlresell.ui.components.buttons.OptionButton
import com.controlresell.ui.components.inputs.Input
import com.controlresell.ui.generated.resources.*
import com.controlresell.ui.theme.AppColors
import org.jetbrains.compose.resources.stringResource

// --- Models ---
data class DropdownMenuOption<T>(
    val id: String,
    val label: String,
    val data: T? = null,
    val isDefaultSelected: Boolean = false,
    val isBlocked: Boolean = false,
    val isPopular: Boolean = false,
    val onPress: ((selected: Boolean, blocked: Boolean?) -> Unit)? = null,
)

data class DropdownMenuState<T>(
    val selectedItems: List<DropdownMenuOption<T>> = mutableListOf(),
    val isVisible: Boolean = false,
    val filterInput: String = "",
)

// --- Dropdown Component ---
@Composable
fun <T> DropdownMenu(
    options: List<DropdownMenuOption<T>>,
    title: String? = null,
    label: String? = null,
    containerType: String = "menu",
    maxSelection: Int? = null,
    forceAtLeastOneSelection: Boolean = false,
    closeOnSelectionWhenMaxOneSelection: Boolean = false,
    enableSearch: Boolean = false,
    onElementSelected: ((selectedItems: List<DropdownMenuOption<T>>, allSelection: List<DropdownMenuOption<T>>) -> Unit)? = null,
    onElementRemoved: ((removedItems: List<DropdownMenuOption<T>>, allSelection: List<DropdownMenuOption<T>>) -> Unit)? = null,
    onBlockedElementPressed: ((blockedItem: DropdownMenuOption<T>) -> Unit)? = null,
    getColorForSelection: ((value: DropdownMenuOption<T>, index: Int) -> Color)? = null,
    optionContainerWidth: Int? = null,
    optionContainerBackgroundColor: Color = Color.Black,
) {

    var state by remember { mutableStateOf(DropdownMenuState<T>()) }
    var toggleSize by remember { mutableStateOf(IntSize(0, 0)) }

    // Initialize default selected items
    LaunchedEffect(Unit) {
        val defaults = options
            .filter { it.isDefaultSelected }
            .let { if (maxSelection != null) it.take(maxSelection) else it }
        state = state.copy(selectedItems = defaults.toMutableList())
        defaults.forEach { it.onPress?.invoke(true, false) }
        onElementSelected?.invoke(defaults, state.selectedItems)
    }

    Column(modifier = Modifier.wrapContentSize()) {
        // --- Dropdown Toggle ---
        if (containerType == "menu") {
            MenuItem(
                label = label ?: stringResource(Res.string.dropdown_menu_select),
                customActionElement = {
                    Icon(
                        imageVector = if (state.isVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                },
                hasBorderRadiusPerSide = BorderRadiusPerSide.full,
                onClick = {
                    state = state.copy(isVisible = !state.isVisible)
                },
                cardModifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                    toggleSize = layoutCoordinates.size
                }
            )
        } else {
            OptionButton(
                icon = {
                    Icon(
                        imageVector = if (state.isVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                },
                onClick = {
                    state = state.copy(isVisible = !state.isVisible)
                },
                modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                    toggleSize = layoutCoordinates.size
                }
            )
        }

        // --- Dropdown Menu ---
        if (state.isVisible) {
            Popup(
                offset = IntOffset(
                    x = 0,
                    y = toggleSize.height
                ),
                onDismissRequest = { state = state.copy(isVisible = false) }
            ) {
                Column(
                    modifier = Modifier
                        .width(optionContainerWidth?.dp ?: 300.dp)
                        .background(optionContainerBackgroundColor, RoundedCornerShape(8.dp))
                        .padding(8.dp)
                        .animateContentSize()
                ) {
                    title?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }

                    if (enableSearch) {
                        Input(
                            value = state.filterInput,
                            onValueChange = { state = state.copy(filterInput = it) },
                            placeholder = stringResource(Res.string.dropdown_menu_search),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    if (maxSelection != null && maxSelection > 1) {
                        Text(
                            text = "Maximum $maxSelection choices",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }

                    val filteredOptions = if (state.filterInput.isBlank()) options
                    else options.filter { it.label.contains(state.filterInput, ignoreCase = true) }

                    if (filteredOptions.isEmpty()) {
                        Text(
                            text = stringResource(Res.string.dropdown_menu_no_items),
                            modifier = Modifier.padding(16.dp),
                            color = Color.Gray
                        )
                    } else {
                        LazyColumn {
                            items(filteredOptions) { item ->
                                val isSelected = state.selectedItems.any { it.id == item.id }
                                val mainColor = when {
                                    item.isBlocked -> Color.Gray
                                    isSelected -> getColorForSelection?.invoke(item, state.selectedItems.indexOf(item))
                                        ?: Color.White

                                    else -> Color.White
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clickable {
                                            when {
                                                item.isBlocked -> onBlockedElementPressed?.invoke(item)
                                                isSelected -> {
                                                    if (!(forceAtLeastOneSelection && state.selectedItems.size == 1)) {
                                                        state = state.copy(
                                                            selectedItems = state.selectedItems.filter { it.id != item.id }
                                                        )
                                                        item.onPress?.invoke(false, false)
                                                        onElementRemoved?.invoke(listOf(item), state.selectedItems)
                                                    }
                                                }

                                                else -> {
                                                    if (maxSelection == null || state.selectedItems.size < maxSelection) {
                                                        state = state.copy(
                                                            selectedItems = state.selectedItems + item
                                                        )
                                                        item.onPress?.invoke(true, false)
                                                        onElementSelected?.invoke(listOf(item), state.selectedItems)
                                                        if (closeOnSelectionWhenMaxOneSelection && maxSelection == 1) {
                                                            state = state.copy(isVisible = false)
                                                        }
                                                    }
                                                }
                                            }
                                        },
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = item.label, color = mainColor)
                                    if (item.isPopular) {
                                        OptionButton(
                                            text = stringResource(Res.string.dropdown_menu_popular),
                                            backgroundColor = AppColors.SuccessOpacity16,
                                            textColor = AppColors.Success
                                        )
                                    }
                                    if (isSelected) {
                                        Icon(Icons.Default.Check, contentDescription = null, tint = mainColor)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
