package gr.manolasn.takeaticket.ui.composables.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import gr.manolasn.takeaticket.common.utils.SortByMethod
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortByDropdownMenu(
    selectedSortBy: SortByMethod,
    onSortSelected: (SortByMethod) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedSortBy.value) }

    // Create a list of all sorting options
    val sortOptions = SortByMethod.values()

    ExposedDropdownMenuBox(
        modifier = Modifier.background(color = AppGreyBlack).fillMaxWidth().wrapContentSize(),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text("Sort by") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sortOptions.forEach { sortBy ->
                DropdownMenuItem(
                    text = { Text(sortBy.value) },
                    onClick = {
                        selectedText = sortBy.value
                        expanded = false
                        onSortSelected(sortBy)
                    }
                )
            }
        }
    }
}