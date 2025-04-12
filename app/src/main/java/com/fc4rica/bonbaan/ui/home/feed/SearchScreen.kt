package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.LoadingIndicator
import com.fc4rica.bonbaan.ui.components.SearchBarPlaceholder
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onSearching: (String) -> Unit,
    viewModel: SearchViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
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
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 0.dp
                )
        ) {
            if (state.searchResult.isEmpty() && state.isLoading) {
                LoadingIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { Spacer(Modifier.height(4.dp)) }
                    items(state.searchResult) { service ->
                        RecommendServiceItem(service, onClick = {})
                    }
                    item { Spacer(Modifier.height(4.dp)) }
                }
            }
        }
    }

}

@Composable
fun RecommendServiceItem(service: Service, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .clickable(onClick = { onClick(service.id) })
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = service.attachments.firstOrNull()?.url,
            contentDescription = "service image",
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Row {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier.size(14.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = service.address, style = MaterialTheme.typography.bodySmall)
            }

            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(100)
                    )
                    .padding(horizontal = 16.dp, vertical = 2.dp)
            ) {
                Text(
                    text = service.categories.firstOrNull()?.name ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//@Composable
//fun SearchHistory() {
//    Column(
//        modifier = Modifier
//            .padding(top = 12.dp)
//            .fillMaxWidth()
//            .background(color = MaterialTheme.colorScheme.surface)
//    ) {
//        Text(
//            text = "ประวัติการค้นหา",
//            modifier = Modifier.padding(16.dp),
//            style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary,
//            fontWeight = FontWeight.Bold
//        )
//        HistoryItem("วัดฟ้าประทาน")
//        HistoryItem("วัดดอยคำ ยำอาฟเตอร์ยู")
//        HistoryItem("วัดดูยูมีน ไอดอนโน บัดไอเลิฟยู")
//    }
//
//}
//
//@Composable
//fun HistoryItem(name: String) {
//    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(text = name, style = MaterialTheme.typography.bodyMedium)
//            Icon(
//                imageVector = Icons.Filled.Close,
//                contentDescription = "Close",
//                modifier = Modifier.size(14.dp),
//
//                tint = Color.Black
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchScreenPreview() {
//    SearchScreen()
//}