package com.fc4rica.bonbaan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarInput(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    placeholderText: String = "ค้นหาสถานที่บน"
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .height(40.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester)
                    .padding(end = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                        onSearch(query)
                    }
                ),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            placeholderText,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            )
            if (query.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            onQueryChange("")
                        }
                        .background(Color.Gray, RoundedCornerShape(100))
                        .padding(2.dp),
                )
            }
        }
    }
}

@Composable
fun SearchBarPlaceholder(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "",
    placeholderText: String = "ค้นหาสถานที่บน",
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .height(40.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (text.isNotEmpty()) {
                Text(text, style = MaterialTheme.typography.bodyMedium)
            } else {
                Text(
                    placeholderText,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}