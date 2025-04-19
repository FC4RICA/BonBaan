package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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

data class OrderDetails(
    val serviceName: String,
    val location: String,
    val nameSurname: String,
    val wish: String,
    val dueDate: String,
    val packageName: String,
    val price: Int,
    val items: List<String>,
    val status: String
)

fun getStatusColor(status: String): Color = when (status) {
    "อยุ่ในระหว่างการตรวจสอบ", "รอการชำระเงิน", "อยู่ระหว่างดำเนินการ", "อยู่ระหว่างการยืนยัน" -> Color(0xFFFFC107)
    "การดำเนินการเสร็จสิ้น" -> Color(0xFF4CAF50)
    "อยู่ระหว่างการคืนเงิน", "รายการถูกยกเลิก" -> Color(0xFFF44336)
    else -> Color.Gray
}

@Composable
fun OrderStatusDetailScreen() {
    val mockOrder = OrderDetails(
        serviceName = "พระตรีมูรติ",
        location = "หน้าเซ็นทรัลเวิลด์",
        nameSurname = "อาบิตคำ ชาตรี",
        wish = """
            ไม่ขออะไรมาก ขอให้แฟนผมรักผม เพราะแฟนผมสวยจนสีนกะพริบตา
            สวยจนอยากตักบารา สวยตะคอก สวยแบตจะโกน 
            สวยแบตไม่หายใจ สวยแบบมีรายละเอียด 
            สวยพร่ำเพรื่อสุดๆ สวยพูดสะดุ้งสุดตัว สวยเหมือนไม่มีจริง
        """.trimIndent(),
        dueDate = "09 เมษายน 2568",
        packageName = "แฟนไม่หนี แถมฟรีความสุข",
        price = 89,
        items = listOf("ธูป", "เทียน", "น้ำแดง"),
        status = "รายการถูกยกเลิก"
    )

    val statusColor = when (mockOrder.status) {
        "อยุ่ในระหว่างการตรวจสอบ", "รอการชำระเงิน", "อยู่ระหว่างดำเนินการ", "อยู่ระหว่างการยืนยัน" -> Color(0xFFFF9800)
        "การดำเนินการเสร็จสิ้น" -> Color(0xFF4CAF50)
        "อยู่ระหว่างการคืนเงิน", "รายการถูกยกเลิก" -> Color(0xFFF44336)
        else -> Color.Black
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 30.dp, top = 16.dp, end = 30.dp, bottom = 120.dp)
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
                    text = "รายการสั่งซื้อ",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Text(
                    mockOrder.status,
                    color = statusColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo1),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .clip(RoundedCornerShape(7.dp))
                            .height(100.dp)
                            .width(100.dp)
                            .padding(12.dp, end = 8.dp)
                    )
                    Column(modifier = Modifier.padding(12.dp)) {
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
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = mockOrder.location,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Text("ข้อมูลการบนบาน", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(mockOrder.nameSurname, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Text("คำขอในการบนบาน", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(mockOrder.wish, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Text("สำเร็จภายในวันที่", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(mockOrder.dueDate, style = MaterialTheme.typography.bodyMedium)
            }

            PackageDetailCard(
                name = mockOrder.packageName,
                price = mockOrder.price,
                items = mockOrder.items,
                onClickDetail = { }
            )

            if (mockOrder.status in listOf("อยู่ระหว่างการยืนยัน", "การดำเนินการเสร็จสิ้น", "อยู่ระหว่างการคืนเงิน")) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)
                        .background(MaterialTheme.colorScheme.surface)) {
                        Text("หลักฐาน", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.logo1),
                            contentDescription = "Proof Image",
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }

            if (mockOrder.status == "รายการถูกยกเลิก") {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("สาเหตุการยกเลิก", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("ลูกค้ายกเลิกคำสั่งซื้อ เนื่องจากเปลี่ยนใจ")
                    }
                }
            }
        }

        if (mockOrder.status == "อยู่ระหว่างการยืนยัน") {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 30.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                    ) {
                        Text("ไม่สำเร็จ", color = Color.White)
                    }

                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .weight(2f)
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                    ) {
                        Text("ยืนยันหลักฐาน", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun PackageDetailCard(
    name: String,
    price: Int,
    items: List<String>,
    onClickDetail: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "แพ็คเกจ",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name, style = MaterialTheme.typography.bodyMedium)
                Text(text = "฿ $price", fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isExpanded = !isExpanded
                        onClickDetail()
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "รายละเอียด",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                items.forEach { item ->
                    Text(text = "- $item", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderStatusDetailScreen() {
    OrderStatusDetailScreen()
}
