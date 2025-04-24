package com.fc4rica.bonbaan.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownSelector(
    label: String,
    options: List<T>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    optionToString: (T) -> String
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        BonBaanTextField(
            readOnly = true,
            value = selectedOption?.let(optionToString) ?: label,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .fillMaxWidth(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(8.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainer)
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(optionToString(item)) },
                    onClick = {
                        onOptionSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
