package com.controlresell.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.CaretDown
import com.adamglin.phosphoricons.bold.CaretUp
import com.adamglin.phosphoricons.bold.Check
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Immutable
data class DropdownMenuOption<T>(
    val id: String,
    val label: String,
    val data: T? = null,
    val isDefaultSelected: Boolean = false,
    val isBlocked: Boolean = false,
    val isPopular: Boolean = false,
    val onClick: ((selected: Boolean, blocked: Boolean?) -> Unit)? = null,
)

@Immutable
data class DropdownMenuState<T>(
    val selectedItems: List<DropdownMenuOption<T>> = mutableListOf(),
    val isVisible: Boolean = false,
    val filterInput: String = "",
)

enum class DropdownMenuType { Menu, Icon }

// Note: This component might not be feature-complete yet. So it will get improved over time.

@ExperimentalComposeApi
@Composable
fun <T> DropdownMenu(
    options: List<DropdownMenuOption<T>>,
    title: String? = null,
    label: String? = null,
    containerType: DropdownMenuType = DropdownMenuType.Menu,
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
    modifier: Modifier,
) {

    var state by remember { mutableStateOf(DropdownMenuState<T>()) }
    var toggleSize by remember { mutableStateOf(IntSize(0, 0)) }

    // Initialize default selected items
    LaunchedEffect(Unit) {
        val defaults = options
            .filter { it.isDefaultSelected }
            .let { if (maxSelection != null) it.take(maxSelection) else it }
        state = state.copy(selectedItems = defaults.toMutableList())
        defaults.forEach { it.onClick?.invoke(true, false) }
        onElementSelected?.invoke(defaults, state.selectedItems)
    }

    Column(modifier = modifier.wrapContentSize()) {
        // --- Dropdown Toggle ---
        val icon = if (state.isVisible) PhosphorIcons.Bold.CaretUp else PhosphorIcons.Bold.CaretDown
        if (containerType == DropdownMenuType.Menu) MenuItem(
            label = label ?: stringResource(Res.string.dropdown_menu_select),
            customActionElement = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = LocalColorScheme.current.onSurface
                )
            },
            onClick = {
                state = state.copy(isVisible = !state.isVisible)
            },
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                toggleSize = layoutCoordinates.size
            }
        ) else OptionButton(
            icon = icon,
            onClick = {
                state = state.copy(isVisible = !state.isVisible)
            },
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                toggleSize = layoutCoordinates.size
            }
        )

        // --- Dropdown Menu ---
        if (state.isVisible) Popup(
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
                title?.let { Text(text = it, style = LocalTypography.current.h5) }

                if (enableSearch) {
                    Input(
                        value = state.filterInput,
                        onValueChange = { state = state.copy(filterInput = it) },
                        placeholder = stringResource(Res.string.dropdown_menu_search),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                if (maxSelection != null) Text(
                    text = pluralStringResource(
                        Res.plurals.dropdown_menu_maximum_choices_number, maxSelection,
                        maxSelection
                    ),
                    style = LocalTypography.current.label,
                )

                val filteredOptions = if (state.filterInput.isBlank()) options
                else options.filter { it.label.contains(state.filterInput, ignoreCase = true) }

                if (filteredOptions.isEmpty()) Text(
                    text = stringResource(Res.string.dropdown_menu_no_items),
                    modifier = Modifier.padding(16.dp),
                    color = Color.Gray
                ) else LazyColumn {
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
                                                item.onClick?.invoke(false, false)
                                                onElementRemoved?.invoke(listOf(item), state.selectedItems)
                                            }
                                        }

                                        else -> {
                                            if (maxSelection == null || state.selectedItems.size < maxSelection) {
                                                state = state.copy(
                                                    selectedItems = state.selectedItems + item
                                                )
                                                item.onClick?.invoke(true, false)
                                                onElementSelected?.invoke(listOf(item), state.selectedItems)
                                                if (closeOnSelectionWhenMaxOneSelection && maxSelection == 1) {
                                                    state = state.copy(isVisible = false)
                                                }
                                            }
                                        }
                                    }
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.label,
                                color = mainColor,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            if (item.isPopular) OptionButton(
                                text = stringResource(Res.string.dropdown_menu_popular),
                                style = StatusOptionButtonStyle.copy(
                                    backgroundColor = SuccessOpacity16,
                                    textColor = Success
                                )
                            )
                            if (isSelected) Icon(
                                PhosphorIcons.Bold.Check,
                                contentDescription = null,
                                tint = mainColor
                            )
                        }
                    }
                }
            }
        }
    }
}
