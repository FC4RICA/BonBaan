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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant

//data class OrderDetails(
//    val serviceName: String,
//    val location: String,
//    val nameSurname: String,
//    val wish: String,
//    val dueDate: String,
//    val packageName: String,
//    val price: Int,
//    val items: List<String>,
//    val status: String
//)


//สี status
//fun getStatusColor(status: String): Color = when (status) {
//    "รอรับออเดอร์", "ที่ต้องชำระ", "กำลังดำเนินการ", "ที่ต้องยืนยัน","ที่ต้องรีวิว" -> Color(0xFFFFC107)
//    "ดำเนินการสำเร็จ" -> Color(0xFF4CAF50)
//    "คืนเงิน", "ยกเลิก" -> Color(0xFFF44336)
//    else -> Color.Gray
//}

@Composable
fun  OrdersStatusScreen() {
    val allStatuses = listOf("ทั้งหมด", "รอรับออเดอร์", "ที่ต้องชำระ", "กำลังดำเนินการ", "ที่ต้องยืนยัน", "ที่ต้องรีวิว", "ดำเนินการสำเร็จ", "คืนเงิน", "ยกเลิก")
    var selectedStatus by remember { mutableStateOf("ทั้งหมด") }
    var expanded by remember { mutableStateOf(false) }

    val allOrders = listOf(
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "รอรับออเดอร์"
        ),
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "ที่ต้องชำระ"
        ),
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "กำลังดำเนินการ"
        ),
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "ที่ต้องยืนยัน"
        ),
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "ที่ต้องรีวิว"
        ),
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "ดำเนินการสำเร็จ"
        ),
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "คืนเงิน"
        ),
        OrderDetails(
            serviceName = "พระตรีมูรติ",
            location = "หน้าเซ็นทรัลเวิลด์",
            nameSurname = "อาบิตคำ ชาตรี",
            wish = "อยากให้แฟนรักมากกว่านี้",
            dueDate = "09 เมษายน 2568",
            packageName = "แฟนไม่หนี แถมฟรีความสุข",
            price = 89,
            items = listOf("ธูป", "เทียน", "น้ำแดง"),
            status = "ยกเลิก"
        )
    )

    val filteredOrders = if (selectedStatus == "ทั้งหมด") allOrders else allOrders.filter { it.status == selectedStatus }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Box(
                        modifier = Modifier
                            .width(180.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable { expanded = true }
                            .padding(12.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = selectedStatus)
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.width(180.dp)
                    ) {
                        allStatuses.forEach { status ->
                            DropdownMenuItem(
                                text = { Text(text = status) },
                                onClick = {
                                    selectedStatus = status
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
            filteredOrders.forEach { order ->
                PackageDetailCard(
                    name = order.packageName,
                    price = order.price,
                    items = order.items,
                    location = order.location,
                    serviceName = order.serviceName,
                    status = order.status,
                    statusColor = getStatusColor(order.status),
                    onClickDetail = { }
                )
            }
        }

    }
}


@Composable
fun PackageDetailCard(
    name: String,
    price: Int,
    items: List<String>,
    location: String,
    serviceName: String,
    status: String,
    statusColor: Color,
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
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo1),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .clip(RoundedCornerShape(7.dp))
                        .height(100.dp)
                        .width(100.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = serviceName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = status,
                            color = statusColor,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = location,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "ราคา", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "฿ $price", fontWeight = FontWeight.Medium)
                    }
                }
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

            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)
            ) {
                when (status) {
                    "รอรับออเดอร์" -> {
                        Box(
                            modifier = Modifier
                                .background(Color.LightGray, RoundedCornerShape(12.dp))
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Text(text = "คุณจะได้รับการตรวจสอบภายใน 24 ชั่วโมง", color = Color.DarkGray)
                        }
                    }
                    "ที่ต้องชำระ" -> {
                        BonBaanButton(
                            text = "ชำระเงิน",
                            onClick = { },
                            variant = ButtonVariant.PRIMARY
                        )
                    }
                    "กำลังดำเนินการ" -> {
                        BonBaanButton(
                            text = "ดูสถานะการทำงาน",
                            onClick = { },
                            variant = ButtonVariant.OUTLINED
                        )
                    }
                    "ที่ต้องยืนยัน" -> {
                        BonBaanButton(
                            text = "ดูหลักฐานการทำงาน",
                            onClick = { },
                            variant = ButtonVariant.OUTLINED
                        )
                    }
                    "ที่ต้องรีวิว" -> {
                        BonBaanButton(
                            text = "รีวิวบริการ",
                            onClick = { },
                            variant = ButtonVariant.OUTLINED
                        )
                    }
                    "ดำเนินการสำเร็จ", "คืนเงิน" -> {
                        BonBaanButton(
                            text = "ซื้ออีกครั้ง",
                            onClick = { },
                            variant = ButtonVariant.OUTLINED
                        )
                    }
                    "ยกเลิก" -> {
                        BonBaanButton(
                            text = "ดูคำขอการยกเลิก",
                            onClick = { },
                            variant = ButtonVariant.OUTLINED
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewOrderStatusScreen() {
    OrdersStatusScreen()
}
