package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.Attachment
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.ui.components.ServiceCard
import com.fc4rica.bonbaan.utils.CategoryUtils

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

val categoriesList = listOf(
    Category(
        id = "1",
        name = "การเรียน",
    ),
    Category(
        id = "2",
        name = "การงาน",
    ),
    Category(
        id = "3",
        name = "ความรัก",
    ),
    Category(
        id = "4",
        name = "ครอบครัว",
    ),
    Category(
        id = "5",
        name = "สุขภาพ",
    ),
    Category(
        id = "6",
        name = "การเงิน",
    ),
    Category(
        id = "7",
        name = "การค้าขาย",
    )
)

@Composable
fun FeedScreen() {
    var searchValue by remember { mutableStateOf("") }
    val categories = CategoryUtils.mapCategoriesIcon(categoriesList)

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
                verticalArrangement = Arrangement.spacedBy(0.dp),
                horizontalArrangement = Arrangement.spacedBy(0.dp)

            ) {
                item(span = { GridItemSpan(2) }) {
                    CategoryRow(categories)
                }

                items(services) { service ->
                    ServiceCard(service)
                }
            }
        }
    }
}

@Composable
fun CategoryRow(categories: List<Category> = listOf()) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            CategoryButton(category, {})
        }
    }
}

@Composable
fun CategoryButton(category: Category, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = { onClick(category.id) })
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(100)
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = category.icon ?: Icons.Filled.Error,
                contentDescription = "Briefcase",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FeedScreen()
}