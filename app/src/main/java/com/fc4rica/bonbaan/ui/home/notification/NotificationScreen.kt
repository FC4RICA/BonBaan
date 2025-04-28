package com.fc4rica.bonbaan.ui.home.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.Notification
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NotificationScreen() {
    val notificationList = listOf(
        Notification(
            "1",
            "คำสั่งของคุณได้รับการยืนยันแล้ว",
            "คำสั่งซื้อหมายเลข 35261728384 อยู่ในขั้นตอนการจัดเตรียม",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "2",
            "คำสั่งของคุณกำลังดำเนินการ",
            "คำสั่งซื้อหมายเลข 35261728384 กำลังจัดเตรียม",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "3",
            "คำสั่งของคุณจัดส่งแล้ว",
            "คำสั่งซื้อหมายเลข 35261728384 จัดส่งเรียบร้อย",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "1",
            "คำสั่งของคุณได้รับการยืนยันแล้ว",
            "คำสั่งซื้อหมายเลข 35261728384 อยู่ในขั้นตอนการจัดเตรียม",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "2",
            "คำสั่งของคุณกำลังดำเนินการ",
            "คำสั่งซื้อหมายเลข 35261728384 กำลังจัดเตรียม",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "3",
            "คำสั่งของคุณจัดส่งแล้ว",
            "คำสั่งซื้อหมายเลข 35261728384 จัดส่งเรียบร้อย",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "1",
            "คำสั่งของคุณได้รับการยืนยันแล้ว",
            "คำสั่งซื้อหมายเลข 35261728384 อยู่ในขั้นตอนการจัดเตรียม",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "2",
            "คำสั่งของคุณกำลังดำเนินการ",
            "คำสั่งซื้อหมายเลข 35261728384 กำลังจัดเตรียม",
            false,
            LocalDateTime.now(),
            ""
        ),
        Notification(
            "3",
            "คำสั่งของคุณจัดส่งแล้ว",
            "คำสั่งซื้อหมายเลข 35261728384 จัดส่งเรียบร้อย",
            false,
            LocalDateTime.now(),
            ""
        ), 
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(bottom = 1.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "การแจ้งเตือน",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { Spacer(Modifier.height(0.dp)) }
            items(notificationList) { notification ->
                NotificationItem(notification = notification, onClick = {})
            }
            item { Spacer(Modifier.height(0.dp)) }
        }
    }
}

@Composable
fun NotificationItem(notification: Notification, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(notification.id) }
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {

        Text(
            text = notification.header,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = notification.createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = notification.body, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    NotificationScreen()
}