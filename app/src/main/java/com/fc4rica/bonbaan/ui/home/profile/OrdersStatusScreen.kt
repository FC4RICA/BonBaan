package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant

@Composable
fun OrdersStatusScreen() {
    val allStatuses = listOf(
        "ทั้งหมด",
        "รอรับออเดอร์",
        "ที่ต้องชำระ",
        "กำลังดำเนินการ",
        "ที่ต้องยืนยัน",
        "ที่ต้องรีวิว",
        "ดำเนินการสำเร็จ",
        "คืนเงิน",
        "ยกเลิก"
    )
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

    val filteredOrders =
        if (selectedStatus == "ทั้งหมด") allOrders else allOrders.filter { it.status == selectedStatus }
    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = { },
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "รายการสั่งซื้อ",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        StatusSelector(
                            options = allStatuses,
                            selectedOption = selectedStatus,
                            onOptionSelected = { selectedStatus = it },
                            optionToString = { it }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { Spacer(Modifier.height(0.dp)) }
            items(filteredOrders) { order ->
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
            item { Spacer(Modifier.height(0.dp)) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusSelector(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    optionToString: (String) -> String
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .border(1.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(8.dp))
        ) {
            Box(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
                Text(
                    text = selectedOption?.let(optionToString) ?: "ทั้งหมด",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(modifier = Modifier.padding(12.dp)) {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(8.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainer)
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(optionToString(item)) },
                    onClick = {
                        onOptionSelected(item)
                        expanded = false
                    }
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
                            Text(
                                text = "คุณจะได้รับการตรวจสอบภายใน 24 ชั่วโมง",
                                color = Color.DarkGray
                            )
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
