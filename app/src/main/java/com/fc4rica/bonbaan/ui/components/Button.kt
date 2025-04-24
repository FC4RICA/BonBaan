package com.fc4rica.bonbaan.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ButtonVariant { PRIMARY, SECONDARY, OUTLINED, TEXT, DESTRUCTIVE }

@Composable
fun BonBaanButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    isEnabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
) {
    val colors = when (variant) {
        ButtonVariant.PRIMARY -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.LightGray
        )
        ButtonVariant.SECONDARY -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = Color.LightGray
        )
        ButtonVariant.OUTLINED -> ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = Color.Gray
        )
        ButtonVariant.TEXT -> ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = Color.Gray
        )
        ButtonVariant.DESTRUCTIVE -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError,
            disabledContainerColor = Color.Gray
        )
    }

    val border = if (variant == ButtonVariant.OUTLINED) {
        BorderStroke(1.dp, if (isEnabled) MaterialTheme.colorScheme.primary else Color.Gray)
    } else {
        null
    }

    Button(
        onClick = onClick,
        colors = colors,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        enabled = isEnabled,
        border = border,
        contentPadding = contentPadding
    ) {
        Text(text)
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
) {
    val colors = when (variant) {
        ButtonVariant.PRIMARY -> IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Gray
        )
        ButtonVariant.SECONDARY -> IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Gray
        )
        else -> null
    }

    IconButton(
        onClick = onClick,
        colors = colors!!,
        modifier = modifier.size(32.dp),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "back button",
            modifier = Modifier.size(20.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBonBaanButton() {
    BackButton(onClick = {})
}