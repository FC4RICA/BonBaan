package com.fc4rica.bonbaan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.User
import java.time.LocalDateTime

@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = review.user?.username ?: "ไม่ระบุชื่อผู้ใช้",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Row {
                repeat(5) {
                    val color =
                        if (it < review.rating) MaterialTheme.colorScheme.secondary else Color.LightGray
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = color,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = review.detail,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ReviewCardPreview() {
    ReviewCard(
        review = Review(
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
}