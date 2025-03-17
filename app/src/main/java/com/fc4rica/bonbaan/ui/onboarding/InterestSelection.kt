package com.fc4rica.bonbaan.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InterestSelection(
    interests: List<Pair<String, Int>>,
    selectedInterests: Set<String>,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        interests.forEach { (label, iconRes) ->
            FilterChip(label, iconRes, selectedInterests.contains(label)) {
                onSelect(label)
            }
        }
    }
}
