package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ReviewItem(
    val profile:String,
    val name:String,
    val packageName:String,
    val rating: Int,
    val comment: String
)

@Composable
fun PackageReviewScreen() {
    val reviewItems = listOf(ReviewItem("Profile", "หมีเนย", "คนคุยมาแน่", 5, "มะกี้เหงามาก อยู่ดีๆก้อมีคนชวรคุย"),
        ReviewItem("Profile", "หมูเด้ง", "คนคุยมาแน่", 4, "ก็ดี"),
        ReviewItem("Profile", "มาดามจือ", "คนคุยมาแน่", 2, "ขอไป 5 นาทีแร้ว ไหนอะไม่เหรมีคัยมาคุยเรย"),
        ReviewItem("Profile", "หมีเนย", "คนคุยมาแน่", 5, "มะกี้เหงามาก อยู่ดีๆก้อมีคนชวรคุย"),
        ReviewItem("Profile", "หมูเด้ง", "คนคุยมาแน่", 4, "ก็ดี"),
        ReviewItem("Profile", "มาดามจือ", "คนคุยมาแน่", 2, "ขอไป 5 นาทีแร้ว ไหนอะไม่เหรมีคัยมาคุยเรย"),
        ReviewItem("Profile", "หมีเนย", "คนคุยมาแน่", 5, "มะกี้เหงามาก อยู่ดีๆก้อมีคนชวรคุย"),
        ReviewItem("Profile", "หมูเด้ง", "คนคุยมาแน่", 4, "ก็ดี"),
        ReviewItem("Profile", "มาดามจือ", "คนคุยมาแน่", 2, "ขอไป 5 นาทีแร้ว ไหนอะไม่เหรมีคัยมาคุยเรย"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(start = 30.dp)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = "รีวิว",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(reviewItems.size) { index ->
                ReviewCard(reviewItem = reviewItems[index])
            }
        }
    }
}

@Composable
fun ReviewCard(reviewItem: ReviewItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = reviewItem.profile)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = reviewItem.name, style = MaterialTheme.typography.bodyMedium)
        }

        Text(text = reviewItem.packageName, style = MaterialTheme.typography.bodyMedium)

        Row {
            repeat(5) { index ->
                Icon(
                    imageVector = if (index < reviewItem.rating)
                        Icons.Rounded.Star
                    else
                        Icons.Rounded.StarBorder,
                    contentDescription = null,
                    tint = if (index < reviewItem.rating) Color(0xFF8B00FF) else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = reviewItem.comment, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPackageReviewScreen() {
    PackageReviewScreen(
    )

}