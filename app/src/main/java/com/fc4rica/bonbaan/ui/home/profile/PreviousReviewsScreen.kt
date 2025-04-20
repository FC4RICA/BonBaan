package com.fc4rica.bonbaan.ui.home.profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.Service
import java.time.LocalDateTime


@Composable
fun NyReviewScreen() {
    val reviewItems = listOf(
        Review(
            id = "",
            user = null,
            service = Service(
                id = "",
                name = "service name",
                description = "service description",
                rate = 0.0,
                address = ""
            ),
            rating = 5.0,
            detail = "มะกี้เหงามาก อยู่ดีๆก้อมีคนชวรคุย",
            createdAt = LocalDateTime.now()
        ),
        Review(
            id = "",
            user = null,
            service = Service(
                id = "",
                name = "service name",
                description = "service description",
                rate = 0.0,
                address = ""
            ),
            rating = 4.0,
            detail = "ก็ดี",
            createdAt = LocalDateTime.now()
        ),
        Review(
            id = "",
            user = null,
            service = Service(
                id = "",
                name = "service name",
                description = "service description",
                rate = 0.0,
                address = ""
            ),
            rating = 2.0,
            detail = "ขอไป 5 นาทีแร้ว ไหนอะไม่เหรมีคัยมาคุยเรย",
            createdAt = LocalDateTime.now()
        ),
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
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = "รายการรีวิวของฉัน",
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
            items(reviewItems) { review ->
                MyReviewCard(review)
            }
        }
    }
}

@Composable
fun MyReviewCard(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = review.service!!.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            repeat(5) { index ->
                Icon(
                    imageVector = if (index < review.rating)
                        Icons.Rounded.Star
                    else
                        Icons.Rounded.StarBorder,
                    contentDescription = null,
                    tint = if (index < review.rating) Color(0xFF8B00FF) else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = review.detail, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyReviewScreen() {
    NyReviewScreen(
    )

}