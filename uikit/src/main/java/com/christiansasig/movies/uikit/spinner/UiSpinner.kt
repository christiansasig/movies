package com.christiansasig.movies.uikit.spinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiSpinner(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    items: List<Any>,
    itemSelected: Any? = null,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    onChangeSelectedListener: (Any) -> Unit,
) {
    if (items.isEmpty()) return

    var isExpanded by remember { mutableStateOf(expanded) }
    var selectedOptionText by remember(itemSelected) {
        mutableStateOf(itemSelected ?: items[0])
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText.toString(),
            onValueChange = {},
            label = { Text("") },
            shape = shape,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            textStyle = MaterialTheme.typography.bodySmall
        )
        ExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.toString()) },
                    onClick = {
                        selectedOptionText = selectionOption
                        isExpanded = false
                        onChangeSelectedListener(selectionOption)
                    }
                )
            }
        }
    }
}