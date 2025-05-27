package com.fc4rica.bonbaan.ui.home.vow_record


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fc4rica.bonbaan.domain.model.Attachment
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.OrderType
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import java.time.LocalDateTime

val record = VowRecord(
    id = "",
    service = Service(
        id = "8590d2a2-fc88-47f0-98f0-25fff89a8e31",
        name = "พระตรีมูรติ",
        description = "พระตรีมูรติ เป็นเทพเจ้าสามองค์ในศาสนาฮินดู ได้แก่ พระพรหมม พระวิษณุ และพระชิวามหาเทพ ซึ่งเป็นที่เคารพบูชาในการขอพรเรื่องความรักและโชคลาภ โดยเฉพาะในเรื่อง ความรักที่สมหวัง การงาน  และความสัมพันธ์ที่ยั่งยืน",
        rate = 4.0,
        address = "เซ็นทรัลเวิลด์ กรุงเทพฯ",
        attachments = listOf(
            Attachment(
                "",
                "https://firebasestorage.googleapis.com/v0/b/webpro-421315.firebasestorage.app/o/images%2F1748189066412131587_unnamed.jpg?alt=media&token=8b575a4e-40b2-43a8-9f09-56f0b8e1f8c3"
            )
        )
    ),
    createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
    vow = "ขอให้ได้คบกับคนคุย",
    deadline = LocalDateTime.of(2025, 4, 10, 0, 0),
    note = "",
    vowOrder = Order(
        id = "da6e2823-cf98-4151-a0ea-54007a63ba3d",
        price = 499.0,
        createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
        status = Status(id = "1", name = "ยังไม่ได้แก้บน"),
        packageItem = Package(
            id = "7da5f1c7-790b-47f5-bc4b-5ff02af2f607",
            name = "แพ็คเกจเริ่มต้น",
            description = "แพ็คเกจเริ่มต้น สำหรับการบนบาน",
            price = 499.0,
            items = listOf(
                "เทียนแดง 1 คู่",
                " ธูปแดง 9 ดอก",
                " ดอกกุหลาบแดง 9 ดอก",
                " ผลไม้สีแดง"
            ),
            orderType = OrderType(id = "1", name = "บนบาน"),
            service = null
        ),
        items = listOf(
            "เทียนแดง 1 คู่",
            " ธูปแดง 9 ดอก",
            " ดอกกุหลาบแดง 9 ดอก",
            " ผลไม้สีแดง"
        ),
        transaction = null,
        cancellationReason = null,
        service = null
    ),
    fulfillOrder = null
)

@Composable
fun VowRecordDetailScreen(
    onClickService: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val status = if (record.fulfillOrder != null) {
        "แก้บนแล้ว"
    } else if (record.deadline <= LocalDateTime.now()) {
        "เกินเวลาที่ขอแล้ว"
    } else {
        "ยังไม่แก้บน"
    }

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = onBackClick,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "บันทึกการบน",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            text = status,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(top = 1.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 12.dp, horizontal = 24.dp),
            ) {
                BonBaanButton(
                    text = if (record.fulfillOrder != null) "ซื้ออีกครั้ง" else "แก้บนเลย",
                    onClick = { record.service?.id?.let { onClickService(it) } },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            VowRecordDetail(record)

            if (record.vowOrder != null) {
                OrderDetail(record.vowOrder, "รายละเอียดคำสั่งซื้อการบนบาน")
            }

            if (record.fulfillOrder != null) {
                OrderDetail(record.fulfillOrder, "รายละเอียดคำสั่งซื้อการแก้บน")
            }
        }
    }
}

@Composable
fun VowRecordDetail(record: VowRecord) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 12.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = record.service?.attachments?.firstOrNull()?.url,
                contentDescription = "Service Image",
                modifier = Modifier
                    .size(82.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = record.service?.name ?: "",
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
                        text = record.service?.address ?: "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "\"${record.vow}\"",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "วันที่บน : ${record.vowOrder?.createdAt?.toLocalDate() ?: "ไม่ระบุ"}",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "ขอบเขต : ${record.deadline.toLocalDate()}",
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!record.note.isNullOrEmpty()) {
            Text(
                text = "โน้ตของคุณ",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${record.note}",
            )
        }
    }
}

@Composable
fun OrderDetail(
    order: Order,
    title: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 12.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "หมายเลขคำสั่งซื้อ : ${order.id}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "ราคา : ${order.price}฿", fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "แพ็คเกจ : ${order.packageItem?.name ?: "แพ็กเกจแบบกำหนดเอง"}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "รายละเอียดแพ็คเกจ",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        order.items.forEach { item ->
            Text(text = "- $item", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecordScreen() {
    VowRecordDetailScreen({}, {})
}
