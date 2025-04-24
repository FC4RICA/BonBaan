package com.fc4rica.bonbaan.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerInputField(
    modifier: Modifier = Modifier,
    selectedDate: String? = null,
    onDateSelected: (String?) -> Unit,
    dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
) {
    var showDatePicker by remember { mutableStateOf(false) }

    val initialDate = remember(selectedDate) {
        selectedDate?.let {
            runCatching { LocalDate.parse(it, dateFormatter) }.getOrNull()
        }
    }

    var internalDate by remember { mutableStateOf(initialDate) }

    val formattedDate = internalDate?.format(dateFormatter) ?: ""

    Box {
        OutlinedTextField(
            value = formattedDate,
            onValueChange = {}, // Read-only field
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Open date picker"
                )
            }
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { showDatePicker = true }
        )
    }

    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { date ->
                internalDate = date
                onDateSelected(date?.format(dateFormatter))
            },
            onDismiss = { showDatePicker = false },
            initialDate = internalDate
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (LocalDate?) -> Unit,
    onDismiss: () -> Unit,
    initialDate: LocalDate? = null
) {
    val millis = initialDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = millis)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
                    Instant.ofEpochMilli(millis)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                }
                onDateSelected(selectedDate)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}