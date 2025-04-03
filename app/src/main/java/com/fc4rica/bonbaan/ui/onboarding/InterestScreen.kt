package com.fc4rica.bonbaan.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.ui.components.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun InterestScreen(
    onSuccess: () -> Unit,
    viewModel: InterestViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getInterests()
    }

    LaunchedEffect(state.isSuccessful) {
        if (state.isSuccessful) {
            onSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(0.3f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo1),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp)
                )
            }
            Text(
                text = "เลือกหัวข้อที่คุณสนใจในการบน",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "เราจะใช้ข้อมูลนี้ในการแนะนำสถานที่บนให้กับคุณ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        InterestSelection(state.categories, state.selectedInterests) { categoryId ->
            viewModel.selectInterest(categoryId)
        }
        Spacer(modifier = Modifier.height(48.dp))
        BonBaanButton(
            text = "ยืนยัน",
            onClick = { viewModel.submitInterests() },
            variant = ButtonVariant.PRIMARY,
            modifier = Modifier.fillMaxWidth(),
            isEnabled = state.selectedInterests.isNotEmpty()
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestSelection(
    categories: List<Category>,
    selectedInterests: List<String>,
    onSelect: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            FilterChip(
                category.name,
                category.icon,
                selectedInterests.contains(category.id)
            ) {
                onSelect(category.id)
            }
        }
    }
}
