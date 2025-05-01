package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.LoadingIndicator
import com.fc4rica.bonbaan.ui.components.SearchBarPlaceholder
import com.fc4rica.bonbaan.ui.components.ServiceCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilteredServiceScreen(
    onBackClick: () -> Unit,
    onClickService: (String) -> Unit,
    onSearching: (String) -> Unit,
    viewModel: FilteredServiceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val selectedTabIndex = when (state.sortType) {
        SortType.Recommend -> 0
        SortType.Popular -> 1
        SortType.Rating -> 2
    }

    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                // Trigger when user scrolls within 3 items of the end
                if (lastVisibleItem >= totalItems - 3 &&
                    !state.isPaginating &&
                    !state.isEndReached
                ) {
                    viewModel.getMoreService()
                }
            }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer),
        topBar = {
            BackNavBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                onBackClick = onBackClick,
                content = {
                    SearchBarPlaceholder(
                        onClick = { onSearching(state.query) },
                        text = state.query,
                    )
                }
            )
        }
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
            FilterTabs(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    when (index) {
                        0 -> viewModel.setSortType(SortType.Recommend)
                        1 -> viewModel.setSortType(SortType.Popular)
                        2 -> viewModel.setSortType(SortType.Rating)
                    }
                }
            )
            if (state.isLoading && state.services.isEmpty()) {
                LoadingIndicator()
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceContainer),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { Spacer(Modifier.height(0.dp)) }
                    items(state.services) { service ->
                        Box(Modifier.padding(horizontal = 8.dp)){
                            ServiceCard(service, onClickService)
                        }
                    }
                    item { Spacer(Modifier.height(0.dp)) }
                }
            }
        }
    }
}

@Composable
fun FilterTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("แนะนำ", "ขายดี", "รีวิวดี")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        indicator = { tabPositions ->
            SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(2.dp),
                color = MaterialTheme.colorScheme.primary
            )
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}