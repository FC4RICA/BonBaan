package com.fc4rica.bonbaan.ui.home.vow_record

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val records = listOf(
    VowRecord(
        id = "",
        service = Service("123", "วัดหลวงพ่อโสธร", "อิอิอิอิ", 0.0, "หน้าเซนทรัลเวิลด์"),
        createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
        vow = "ขอ A นะนะนะ...",
        deadline = LocalDateTime.of(2025, 6, 12, 0, 0),
        note = "",
        vowOrder = Order(
            id = "12345678",
            price = 899.0,
            createdAt = LocalDateTime.of(2025, 6, 12, 0, 0),
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
        service = Service(
            id = "8590d2a2-fc88-47f0-98f0-25fff89a8e31",
            name = "พระตรีมูรติ",
            description = "พระตรีมูรติ เป็นเทพเจ้าสามองค์ในศาสนาฮินดู ได้แก่ พระพรหมม พระวิษณุ และพระชิวามหาเทพ ซึ่งเป็นที่เคารพบูชาในการขอพรเรื่องความรักและโชคลาภ โดยเฉพาะในเรื่อง ความรักที่สมหวัง การงาน  และความสัมพันธ์ที่ยั่งยืน",
            rate = 4.0,
            address = "เซ็นทรัลเวิลด์ กรุงเทพฯ",
        ),
        createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
        vow = "ขอให้ได้คบกับคนคุย",
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
        service = Service(
            id = "8590d2a2-fc88-47f0-98f0-25fff89a8e31",
            name = "พระตรีมูรติ",
            description = "พระตรีมูรติ เป็นเทพเจ้าสามองค์ในศาสนาฮินดู ได้แก่ พระพรหมม พระวิษณุ และพระชิวามหาเทพ ซึ่งเป็นที่เคารพบูชาในการขอพรเรื่องความรักและโชคลาภ โดยเฉพาะในเรื่อง ความรักที่สมหวัง การงาน  และความสัมพันธ์ที่ยั่งยืน",
            rate = 4.0,
            address = "เซ็นทรัลเวิลด์ กรุงเทพฯ",
        ),
        createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
        vow = "ขอให้ได้คุยกับคนที่แอบชอบ",
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
        fulfillOrder = Order(
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
        )
    )
)

@Composable
fun VowRecordScreen(
    onClickVowRecord: (String) -> Unit,
    onClickService: (String) -> Unit
) {
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
                    text = "บันทึกการบน",
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
            item { }
            items(records) { record ->
                OrderStatusCard(
                    record = record,
                    onClick = onClickVowRecord,
                    onClickService = onClickService
                )
            }
            item { }
        }
    }
}

@Composable
fun OrderStatusCard(
    record: VowRecord,
    onClick: (String) -> Unit,
    onClickService: (String) -> Unit
) {
    val status = if (record.fulfillOrder != null) {
        "แก้บนแล้ว"
    } else if (record.deadline <= LocalDateTime.now()) {
        "เกินเวลาที่ขอแล้ว"
    } else {
        "ยังไม่แก้บน"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(record.id) }
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = record.service?.name ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = record.vow,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (record.deadline > LocalDateTime.now()) {
                Text(
                    text = record.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (record.fulfillOrder == null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "คำขอของคุณสำเร็จภายในเวลาหรือไม่?",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                BonBaanButton(
                    text = "แก้บน",
                    onClick = { record.service?.let { onClickService(it.id) } },
                    variant = ButtonVariant.OUTLINED
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVowRecordScreen() {
    VowRecordScreen({}, {})
}