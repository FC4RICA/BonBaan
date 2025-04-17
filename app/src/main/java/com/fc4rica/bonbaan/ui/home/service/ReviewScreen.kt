package com.fc4rica.bonbaan.ui.home.profile

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant

data class Order(
    val serviceName: String,
    val location : String,
    val packageName: String,
    val price: String,
    val Date: String,
    val items: List<String>
)
val mockOrder = Order(
    serviceName = "ศาลเจ้าพ่อเสือ",
    location ="กรุงเทพ",
    packageName = "เนื้อคู่มาแน่",
    price = "89",
    Date = "9 เมษายน 2567",
    items = listOf("ธูป 9 ดอก", "พวงมาลัย 1 พวง", "เทียน 2 เล่ม")
)

@Composable
fun WriteReviewScreen(
    serviceName: String,
    location: String,
    packageName: String,
    Date: String,
    items: List<String>,
    onSubmitReview: (rating: Int, comment: String) -> Unit
) {
    var rating by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
                .padding(horizontal = 30.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
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
                    text = "รีวิว",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo1),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(7.dp)
                            )
                            .height(80.dp)
                            .width(80.dp)
                            .padding(12.dp, end = 8.dp)

                    )

                    Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                        Text(
                            text = mockOrder.serviceName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = "Location",
                                modifier = Modifier.size(14.dp),
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = mockOrder.location,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Spacer(modifier = Modifier.height(8.dp))


                    }

                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "ใช้บริการวันที่ : $Date", style = MaterialTheme.typography.bodyMedium)
                Text(text = "ขอบเขต: $Date", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "แพ็คเกจ: $packageName", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(text = "รายละเอียดแพ็คเกจ :")

                items.forEach { item ->
                    Text(text = "- $item", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "ให้คะแนน", fontWeight = FontWeight.Bold)
                RatingBar(currentRating = rating, onRatingChanged = { rating = it })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = { Text("ความคิดเห็น") },
                    placeholder = { Text("เขียนรีวิวของคุณที่นี่...") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    BonBaanButton(
                        modifier = Modifier.width(200.dp),
                        text = "รีวิวเลย",
                        onClick = { },
                        variant = ButtonVariant.PRIMARY
                    )
                }
            }
        }
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
                imageVector = if (i <= currentRating) Icons.Rounded.Star else Icons.Rounded.StarBorder,
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
    WriteReviewScreen(
        serviceName = mockOrder.serviceName,
        packageName = mockOrder.packageName,
        location = mockOrder.location,
        Date = mockOrder.Date,
        items = listOf("ธูป 9 ดอก", "พวงมาลัย 1 พวง"),
        onSubmitReview = { rating, comment ->
            Log.d("Review", "Rating: $rating, Comment: $comment")
        }
    )

}