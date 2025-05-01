package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.*
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import java.time.LocalDateTime



@Composable
fun VowRecordScreen() {
    val records = listOf(
        VowRecord(
            id = "",
            service = Service("123", "วัดหลวงพ่อโสธร", "อิอิอิอิ", 0.0, "หน้าเซนทรัลเวิลด์"),
            createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
            vow = "ขอ A นะนะนะ...",
            deadline = LocalDateTime.of(2025, 5, 12, 0, 0),
            note = "แฟนคนที่ 24",
            vowOrder = Order(
                id = "12345678",
                price = 899.0,
                createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
                status = Status(id = "1", name = "ยังไม่ได้แก้บน"),
                packageItem = Package(
                    id = "123ไอดีแพ้คเก็จ",
                    name = "ชุดไหว้พระตรีมูรติ",
                    description = "แพ็คเกจประกอบด้วยดอกไม้ ธูป เทียน",
                    price = 899.0,
                    items = listOf("ธูป 9 ดอก", "เทียนสีแดง", "ดอกกุหลาบแดง 9 ดอก"),
                    orderType = OrderType(id = "1", name = "บนบาน"),
                    service = Service("srv-001", "พระตรีมูรติ", "อิอิอิอิ", 0.0, "หน้าเซนทรัลเวิลด์")
                ),
                transaction = null,
                cancellationReason = null,
                service = null
            ),
            fulfillOrder = null
        ),
        VowRecord(
            id = "",
            service = Service("123", "พระตรีมูรติ", "อิอิอิอิ", 0.0, "หน้าเซนทรัลเวิลด์"),
            createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
            vow = "ผมขอให้คนไทยยิ้มได้",
            deadline = LocalDateTime.of(2025, 4, 20, 0, 0),
            note = "แฟนคนที่ 24",
            vowOrder = Order(
                id = "12345678",
                price = 899.0,
                createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
                status = Status(id = "2", name = "หมดเวลา"),
                packageItem = Package(
                    id = "123ไอดีแพ้คเก็จ",
                    name = "ชุดไหว้พระตรีมูรติ",
                    description = "แพ็คเกจประกอบด้วยดอกไม้ ธูป เทียน",
                    price = 899.0,
                    items = listOf("ธูป 9 ดอก", "เทียนสีแดง", "ดอกกุหลาบแดง 9 ดอก"),
                    orderType = OrderType(id = "1", name = "บนบาน"),
                    service = Service("srv-001", "พระตรีมูรติ", "อิอิอิอิ", 0.0, "หน้าเซนทรัลเวิลด์")
                ),
                transaction = null,
                cancellationReason = null,
                service = null
            ),
            fulfillOrder = null
        ),
        VowRecord(
            id = "",
            service = Service("123", "พระตรีมูรติ2", "อิอิอิอิ", 0.0, "หน้าเซนทรัลเวิลด์"),
            createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
            vow = "ผมขอให้คนไทยยิ้มได้2",
            deadline = LocalDateTime.of(2025, 4, 10, 0, 0),
            note = "แฟนคนที่ 24",
            vowOrder = Order(
                id = "12345678",
                price = 899.0,
                createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
                status = Status(id = "3", name = "แก้บนแล้ว"),
                packageItem = Package(
                    id = "123ไอดีแพ้คเก็จ",
                    name = "ชุดไหว้พระตรีมูรติ",
                    description = "แพ็คเกจประกอบด้วยดอกไม้ ธูป เทียน",
                    price = 899.0,
                    items = listOf("ธูป 9 ดอก", "เทียนสีแดง", "ดอกกุหลาบแดง 9 ดอก"),
                    orderType = OrderType(id = "1", name = "บนบาน"),
                    service = Service("srv-001", "พระตรีมูรติ", "อิอิอิอิ", 0.0, "หน้าเซนทรัลเวิลด์")
                ),
                transaction = null,
                cancellationReason = null,
                service = null
            ),
            fulfillOrder = null
        )
    )

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = {},
                content = {
                    Text(
                        text = "บันทึกการบน",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(
                            start = 18.dp,
                            top = 20.dp,
                            end = 18.dp,
                        )
                ) {

                    records.forEach { record ->
                        OrderStatusCard(
                            name = record.service?.name?: "ไม่ทราบชื่อ",
                            status = record.vowOrder?.status?.name?: "ไม่ทราบสถานะ",
                            vow = record.vow,
                            dueDate = record.deadline.toLocalDate().toString(),
                            onClick = { }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun OrderStatusCard(
    name: String,
    status: String,
    vow: String,
    dueDate: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = status,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = vow,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = dueDate,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if (status == "ยังไม่ได้แก้บน" || status == "หมดเวลา") {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    BonBaanButton(
                        text = "แก้บน",
                        onClick = onClick,
                        variant = ButtonVariant.PRIMARY
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewVowRecordScreen() {
    VowRecordScreen()
}