package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.Attachment
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.ui.components.ServiceCard

val services = listOf(
    Service(
        id = "1",
        name = "Service1",
        description = "description",
        rate = 3.0,
        address = "KMUTT",
        categories = listOf(
            Category(
                id = "1",
                name = "love",
                icon = Icons.Filled.Work
            )
        ),
        packages = listOf(),
        attachments = listOf(Attachment(id = "a", url = "https://picsum.photos/400/300")),
        reviews = listOf(),
    ),
    Service(
        id = "2",
        name = "Service2",
        description = "description",
        rate = 3.0,
        address = "KMUTT",
        categories = listOf(
            Category(
                id = "1",
                name = "love",
                icon = Icons.Filled.Work
            )
        ),
        packages = listOf(),
        attachments = listOf(Attachment(id = "a", url = "https://picsum.photos/400/300")),
        reviews = listOf(),
    ),
    Service(
        id = "1",
        name = "Service3",
        description = "description",
        rate = 3.0,
        address = "KMUTT",
        categories = listOf(
            Category(
                id = "1",
                name = "love",
                icon = Icons.Filled.Work
            )
        ),
        packages = listOf(),
        attachments = listOf(Attachment(id = "a", url = "https://picsum.photos/400/300")),
        reviews = listOf(),
    ),
    Service(
        id = "1",
        name = "Service4",
        description = "description",
        rate = 3.0,
        address = "KMUTT",
        categories = listOf(
            Category(
                id = "1",
                name = "love",
                icon = Icons.Filled.Work
            )
        ),
        packages = listOf(),
        attachments = listOf(Attachment(id = "a", url = "https://picsum.photos/400/300")),
        reviews = listOf(),
    ),
    Service(
        id = "1",
        name = "Service5",
        description = "description",
        rate = 3.0,
        address = "KMUTT",
        categories = listOf(
            Category(
                id = "1",
                name = "love",
                icon = Icons.Filled.Work
            )
        ),
        packages = listOf(),
        attachments = listOf(Attachment(id = "a", url = "https://picsum.photos/400/300")),
        reviews = listOf(),
    ),
    Service(
        id = "1",
        name = "Service6",
        description = "description",
        rate = 3.0,
        address = "KMUTT",
        categories = listOf(
            Category(
                id = "1",
                name = "love",
                icon = Icons.Filled.Work
            )
        ),
        packages = listOf(),
        attachments = listOf(Attachment(id = "a", url = "https://picsum.photos/400/300")),
        reviews = listOf(),
    ),
    Service(
        id = "1",
        name = "Service7",
        description = "description",
        rate = 3.0,
        address = "KMUTT",
        categories = listOf(
            Category(
                id = "1",
                name = "love",
                icon = Icons.Filled.Work
            )
        ),
        packages = listOf(),
        attachments = listOf(Attachment(id = "a", url = "https://picsum.photos/400/300")),
        reviews = listOf(),
    )
)

@Composable
fun FeedScreen() {
    var searchValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchBar(searchValue = searchValue, onValueChange = { searchValue = it })
        },

        ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 0.dp
                )
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
            ) {
                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Category()
                }

                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "แนะนำ",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                items(services) { service ->
                    ServiceCard(service)
                }
            }
        }
    }
}

@Composable
fun Category() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = "หมวดหมู่", modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            SubCategory("ความรัก")
            SubCategory("การงาน")
            SubCategory("ค้าขาย")
            SubCategory("การเงิน")
            SubCategory("สุขภาพ")
        }
    }
}

@Composable
fun SubCategory(name: String) {
    Column(
        modifier = Modifier.padding(top = 12.dp, start = 22.dp, end = 22.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.Work,
            contentDescription = "Briefcase",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(text = name, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FeedScreen()
}