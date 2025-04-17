package com.fc4rica.bonbaan.ui.home.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import com.fc4rica.bonbaan.ui.home.service.VowRecordScreen

@Composable
fun ReviewsScreen(
    serviceName: String,
    packageName: String,
    Date: String,
    onSubmit: (rating: Int, comment: String) -> Unit
) {
    var rating by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 30.dp, top = 16.dp, end = 30.dp, bottom = 100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(start = 0.dp)
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
                text = "เขียนรีวิว",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "สถานที่ : $serviceName",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "แพ็คเกจ : $packageName",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "ขอบเขต : $Date",
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "ให้คะแนน",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        RatingBar(
            currentRating = rating,
            onRatingChanged = { rating = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "แสดงความคิดเห็น",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            placeholder = { Text("พิมพ์ความคิดเห็นของคุณ...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 6,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))


        BonBaanButton(
            text = "รีวิว",
            onClick = {
                onSubmit(rating, comment)
            },
            variant = ButtonVariant.PRIMARY,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun RatingBar(
    currentRating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Default.Star else Icons.Outlined.Star,
                contentDescription = "Star $i",
                tint = if (i <= currentRating) Color(0xFFFFC107) else Color.Gray,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewReviewScreen() {
    ReviewsScreen(
        serviceName = "พระตรีมูรติ เซ็นทรัลเวิลด์",
        packageName = "ขอแฟน แถมความสุข", Date = "9 เมษายน 2567",
        onSubmit = { rating, comment ->

            Log.d("Review", "Rating: $rating, Comment: $comment")
        }
    )

}