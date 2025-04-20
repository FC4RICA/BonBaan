package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.ReviewCard
import java.time.LocalDateTime

@Composable
fun PackageReviewScreen() {

    val reviews = listOf(
        Review(
            id = "1",
            user = User(
                id = "1",
                username = "john_doe",
                firstname = "william",
                lastname = "harrison",
                email = "william.henry.harrison@example.com",
                phone = "0812345678",
            ),
            service = null,
            rating = 3.0,
            detail = "review description",
            createdAt = LocalDateTime.now()
        ),
        Review(
            id = "1",
            user = User(
                id = "1",
                username = "john_doe",
                firstname = "william",
                lastname = "harrison",
                email = "william.henry.harrison@example.com",
                phone = "0812345678",
            ),
            service = null,
            rating = 3.0,
            detail = "review description",
            createdAt = LocalDateTime.now()
        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer),
        topBar = {
            BackNavBar(
                onBackClick = { },
                content = {
                    Text(
                        text = "รีวิว",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = 0.dp
                )
                .padding(8.dp)
        ) {
            items(reviews) { review ->
                ReviewCard(review)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPackageReviewScreen() {
    PackageReviewScreen(
    )

}