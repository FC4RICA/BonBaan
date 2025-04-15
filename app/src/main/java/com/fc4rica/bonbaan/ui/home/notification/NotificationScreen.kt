package com.fc4rica.bonbaan.ui.home.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.ui.MockScreen
import com.fc4rica.bonbaan.ui.home.service.Review

data class NotiDetailItem(
    val Title: String,
    val Date: String,
    val Info: String
)

@Composable
fun NotificationScreen() {
    val notificationList = listOf(
        NotiDetailItem(
            "คำสั่งของคุณได้รับการยืนยันแล้ว",
            "12/02/2568",
            "คำสั่งซื้อหมายเลข 35261728384 อยู่ในขั้นตอนการจัดเตรียม"
        ),
        NotiDetailItem(
            "คำสั่งของคุณกำลังดำเนินการ",
            "13/02/2568",
            "คำสั่งซื้อหมายเลข 35261728384 กำลังจัดเตรียม"
        ),
        NotiDetailItem(
            "คำสั่งของคุณจัดส่งแล้ว",
            "14/02/2568",
            "คำสั่งซื้อหมายเลข 35261728384 จัดส่งเรียบร้อย"
        )
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
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "การแจ้งเตือน",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Column(modifier = Modifier.fillMaxWidth().padding(top = 12.dp).background(MaterialTheme.colorScheme.surface)) {
            notificationList.forEach { notiItem ->
                NotificationItem(notiDetailItem = notiItem)
            }
        }
    }
}

@Composable
fun NotificationItem(notiDetailItem: NotiDetailItem) {
    Spacer(modifier = Modifier.height(12.dp))
    Row(modifier = Modifier.padding(start = 20.dp,end = 20.dp)) {
        Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.LightGray)
    )
//        AsyncImage(
//            model = url,
//            contentDescription = "service image",
//            modifier = Modifier
//                .fillMaxHeight()
//                .aspectRatio(1f)
//                .clip(RoundedCornerShape(4.dp)),
//            contentScale = ContentScale.Crop
//        )

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Text(
                text = notiDetailItem.Title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = notiDetailItem.Date, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = notiDetailItem.Info, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))

        }
    }

    Spacer(modifier = Modifier.width(12.dp))
    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
}


@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    NotificationScreen()
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
