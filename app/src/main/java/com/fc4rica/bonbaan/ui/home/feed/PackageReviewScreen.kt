package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
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
    val name:String,
    val packageName:String,
    val rating: Int,
    val comment: String
)

@Composable
fun PackageReviewScreen() {
    val reviewItems = listOf(
        ReviewItem("หมีเนย","คนคุยมาแน่",5, "มะกี้เหงามาก อยู่ดีๆก้อมีคนชวรคุย"),
        ReviewItem("หมูเด้ง","คนคุยมาแน่",4, "ก็ดี"),
        ReviewItem("มาดามจือ","คนคุยมาแน่",2, "ขอไป 5 นาทีแร้ว ไหนอะไม่เหรมีคัยมาคุยเรย")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.padding(start = 30.dp)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "รีวืว",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        reviewItems.forEach { review ->
            ReviewCard(reviewItem = review)
        }
    }


}





@Composable
fun ReviewCard(reviewItem: ReviewItem){
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        .height(40.dp)

    ) {
        Row() { Text("Profile")
            Text(text = reviewItem.name, style = MaterialTheme.typography.bodyMedium)
             }
        Text(text = reviewItem.packageName, style = MaterialTheme.typography.bodyMedium)

        Row {
            repeat(5) { index ->
                Text(
                    text = if (index < reviewItem.rating) "★" else "☆",
                    color = if (index < reviewItem.rating) Color(0xFF8B00FF) else Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = reviewItem.comment, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPackageReviewScreen() {
    PackageReviewScreen(
    )

}