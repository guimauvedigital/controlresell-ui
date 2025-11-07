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
data class SelectionState<T>(
    val selected: List<T> = emptyList(),
    val excluded: List<T> = emptyList(),
)

enum class DropdownMenuType { Menu, Icon }

@Composable
fun <T> DropdownMenu(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    options: List<T>,
    selectionState: SelectionState<T>,
    onSelectionChange: (SelectionState<T>) -> Unit,
    itemLabel: (T) -> String,
    modifier: Modifier = Modifier,
    itemKey: ((T) -> Any)? = null,
    itemIsBlocked: ((T) -> Boolean)? = null,
    itemIsPopular: ((T) -> Boolean)? = null,
    title: String? = null,
    label: String? = null,
    containerType: DropdownMenuType = DropdownMenuType.Menu,
    maxSelection: Int? = null,
    forceAtLeastOneSelection: Boolean = false,
    closeOnSelectionWhenMaxOneSelection: Boolean = false,
    enableSearch: Boolean = false,
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    enableExclude: Boolean = false,
    onBlockedItemPressed: ((T) -> Unit)? = null,
    getColorForSelection: ((T, Int) -> Color)? = null,
    optionContainerWidth: Int? = null,
    optionContainerBackgroundColor: Color = Color.Black,
) {

    var toggleSize by remember { mutableStateOf(IntSize(0, 0)) }

    Column(modifier = modifier.wrapContentSize()) {
        // --- Dropdown Toggle ---
        val icon = if (expanded) PhosphorIcons.Bold.CaretUp else PhosphorIcons.Bold.CaretDown
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
                onExpandedChange(!expanded)
            },
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                toggleSize = layoutCoordinates.size
            }
        ) else OptionButton(
            icon = icon,
            onClick = {
                onExpandedChange(!expanded)
            },
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                toggleSize = layoutCoordinates.size
            }
        )

        // --- Dropdown Menu ---
        if (expanded) Popup(
            offset = IntOffset(
                x = 0,
                y = toggleSize.height
            ),
            onDismissRequest = { onExpandedChange(false) }
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
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
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

                val filteredOptions = if (searchQuery.isBlank()) options
                else options.filter { itemLabel(it).contains(searchQuery, ignoreCase = true) }

                if (filteredOptions.isEmpty()) Text(
                    text = stringResource(Res.string.dropdown_menu_no_items),
                    modifier = Modifier.padding(16.dp),
                    color = Color.Gray
                ) else LazyColumn {
                    items(
                        items = filteredOptions,
                        key = itemKey ?: { it.hashCode() }
                    ) { item ->
                        val isBlocked = itemIsBlocked?.invoke(item) == true
                        val isPopular = itemIsPopular?.invoke(item) == true
                        val isSelected = selectionState.selected.contains(item)
                        val isExcluded = selectionState.excluded.contains(item)
                        val mainColor = when {
                            isBlocked -> Color.Gray
                            isSelected -> getColorForSelection?.invoke(item, selectionState.selected.indexOf(item))
                                ?: Color.White

                            isExcluded -> Error
                            else -> Color.White
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    when {
                                        isBlocked -> onBlockedItemPressed?.invoke(item)
                                        isSelected -> {
                                            if (!(forceAtLeastOneSelection && selectionState.selected.size == 1)) {
                                                if (enableExclude) {
                                                    // Move from selected to excluded
                                                    val newSelected = selectionState.selected.filter { it != item }
                                                    val newExcluded = selectionState.excluded + item
                                                    onSelectionChange(
                                                        SelectionState(
                                                            selected = newSelected,
                                                            excluded = newExcluded
                                                        )
                                                    )
                                                } else {
                                                    // Just deselect
                                                    val newSelected = selectionState.selected.filter { it != item }
                                                    onSelectionChange(
                                                        selectionState.copy(selected = newSelected)
                                                    )
                                                }
                                            }
                                        }

                                        isExcluded -> {
                                            // Move from excluded to not selected
                                            val newExcluded = selectionState.excluded.filter { it != item }
                                            onSelectionChange(
                                                selectionState.copy(excluded = newExcluded)
                                            )
                                        }

                                        else -> {
                                            if (maxSelection == null || selectionState.selected.size < maxSelection) {
                                                val newSelected = selectionState.selected + item
                                                val shouldClose =
                                                    closeOnSelectionWhenMaxOneSelection && maxSelection == 1

                                                onSelectionChange(
                                                    selectionState.copy(selected = newSelected)
                                                )

                                                if (shouldClose) {
                                                    onExpandedChange(false)
                                                }
                                            }
                                        }
                                    }
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = itemLabel(item),
                                color = mainColor,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            if (isPopular) OptionButton(
                                text = stringResource(Res.string.dropdown_menu_popular),
                                style = StatusOptionButtonStyle.copy(
                                    backgroundColor = SuccessOpacity16,
                                    textColor = Success
                                )
                            )
                            if (isSelected || isExcluded) Icon(
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
