package com.fc4rica.bonbaan.ui.home.service


import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.OrderType
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import java.time.LocalDateTime

@Composable
fun VowRecordDetailScreen() {
    val record = VowRecord(
        id = "",
        service = Service(
            id = "123ไอดีservice",
            name = "พระตรีมูรติ",
            description = "อิอิอิอิ",
            rate = 0.0,
            address = "หน้าเซนทรัลเวิลด์",
        ),
        createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
        vow = "ผมขอให้คนไทยยิ้มได้",
        deadline = LocalDateTime.of(2025, 4, 10, 0, 0),
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
                service = Service(
                    id = "srv-001",
                    name = "พระตรีมูรติ",
                    description = "อิอิอิอิ",
                    rate = 0.0,
                    address = "หน้าเซนทรัลเวิลด์"
                )
            ),
            transaction = null,
            cancellationReason = null,
            service = null
        ),
        fulfillOrder = Order(
            id = "87654321",
            price = 899.0,
            createdAt = LocalDateTime.of(2025, 4, 10, 0, 0),
            status = Status(id = "2", name = "สำเร็จแล้ว"),
            packageItem = Package(
                id = "pkg-001",
                name = "ชุดไหว้พระตรีมูรติ",
                description = "แพ็คเกจประกอบด้วยดอกไม้ ธูป เทียน",
                price = 899.0,
                items = listOf("ธูป 9 ดอก", "เทียนสีแดง", "ดอกกุหลาบแดง 9 ดอก"),
                orderType = OrderType(id = "2", name = "แก้บน"),
                service = Service(
                    id = "123ไอดีแพ้คเก็จ",
                    name = "พระตรีมูรติ",
                    description = "อิอิอิอิ",
                    rate = 0.0,
                    address = "หน้าเซนทรัลเวิลด์"
                )
            ),
            transaction = null,
            cancellationReason = null,
            service = null
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
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 30.dp, vertical = 16.dp)
            ) {
                BonBaanButton(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.Center),
                    text = "แก้บนเลย",
                    onClick = { },
                    variant = ButtonVariant.SECONDARY
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 18.dp,
                    top = 80.dp,
                    end = 18.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(12.dp), clip = true)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(
                        painter = painterResource(id = R.drawable.logo1),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .clip(RoundedCornerShape(7.dp))
                            .height(100.dp)
                            .width(100.dp)
                            .padding(12.dp, end = 8.dp)
                    )

                    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                        Text(
                            text = record.service?.name ?: "ไม่ทราบสถานะ",
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
                                text = record.service?.address ?: "ไม่ทราบสถานที่",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(7.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = record.vowOrder?.status?.name ?: "ไม่ทราบสถานะ",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "\"${record.vow}\"",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "วันที่บน : ${record.vowOrder?.createdAt?.toLocalDate() ?: "ไม่ระบุ"}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "ขอบเขต : ${record.deadline.toLocalDate()}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "โน้ตของคุณ",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = "${record.note}",
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEAEAEA)),
                    textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledBorderColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                )
            }

            val order = record.vowOrder
            if (order != null) {
                PackageDetail(
                    orderid = order.id,
                    name = order.packageItem?.name ?: "ไม่มีชื่อแพ็คเกจ",
                    price = order.price.toInt(),
                    items = order.packageItem?.items ?: listOf("ไม่มีข้อมูล")
                )
            }
        }
    }
}

@Composable
fun PackageDetail(orderid: String, name: String, price: Int, items: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp), clip = true)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "รายละเอียดคำสั่งซื้อ",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "หมายเลขคำสั่งซื้อ : $orderid", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ราคา : ${price}฿", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "แพ็คเกจ : $name", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "รายละเอียดแพ็คเกจ",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            items.forEach { item ->
                Text(text = "- $item", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecordScreen() {
    VowRecordDetailScreen()
}
