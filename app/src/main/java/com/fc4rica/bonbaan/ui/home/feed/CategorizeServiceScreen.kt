package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.LoadingIndicator
import com.fc4rica.bonbaan.ui.components.ServiceCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategorizeServiceScreen(
    onClickBack: () -> Unit,
    onClickService: (String) -> Unit,
    viewModel: CategorizeServiceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            BackNavBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                onBackClick = onClickBack,
                content = {
                    Text(
                        text = state.category?.name ?: "",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 0.dp
                )
        ) {
            if (state.isLoading) {
                LoadingIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { Spacer(Modifier.height(4.dp)) }
                    items(state.services) { service ->
                        ServiceCard(service, onClickService)
                    }
                    item { Spacer(Modifier.height(4.dp)) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategorizeServiceScreenPreview() {
    CategorizeServiceScreen({}, {})
}