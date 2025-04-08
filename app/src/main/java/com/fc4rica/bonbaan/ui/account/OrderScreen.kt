package com.fc4rica.bonbaan.ui.account

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fc4rica.bonbaan.ui.home.HomeScreen
import androidx.compose.ui.unit.dp as dp1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("ทั้งหมด") }

    val orderStatusList = listOf(
        "ทั้งหมด", "รอรับออเดอร์", "ที่ต้องชำระ", "กำลังดำเนินการ", "ที่ต้องยืนยัน",
        "ที่ต้องรีวิว", "สำเร็จ", "คืนเงิน", "ยกเลิก"
    )

    Column(modifier = Modifier.padding(16.dp1)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*  */ }) {
                Icon(painter = painterResource(id = /*  */), contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp1))
            Text(text = "รายการสั่งซื้อ", style = MaterialTheme.typography.headlineLarge)
        }

        Spacer(modifier = Modifier.height(16.dp1))

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                label = { Text("เลือกสถานะ") },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                orderStatusList.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp1))

        OrderItem(title = "ตำบลการสำเร็จ", price = 89, status = "ดำเนินการสำเร็จ")
        OrderItem(title = "กำลังดำเนินการ", price = 89, status = "กำลังดำเนินการ")
        OrderItem(title = "ที่ต้องรีวิว", price = 89, status = "ที่ต้องรีวิว")
        OrderItem(title = "ยกเลิก", price = 89, status = "ยกเลิก")
        OrderItem(title = "คืนเงิน", price = 89, status = "คืนเงิน")
    }
}

@Composable
fun OrderItem(title: String, price: Int, status: String) {
    val label = when (status) {
        "ยกเลิก" -> "ยกเลิก"
        "คืนเงิน" -> "คืนเงิน"
        "ดำเนินการสำเร็จ" -> "ดำเนินการสำเร็จ"
        "ที่ต้องรีวิว" -> "ที่ต้องรีวิว"
        "กำลังดำเนินการ" -> "กำลังดำเนินการ"
        else -> "ที่ต้องชำระ"
    }

    val buttonText = when (status) {
        "ยกเลิก" -> "ดูคำการยกเลิก"
        "คืนเงิน" -> "ซื้ออีกครั้ง"
        "ดำเนินการสำเร็จ" -> "ซื้ออีกครั้ง"
        "ที่ต้องรีวิว" -> "รีวิวบริการ"
        else -> "ชำระเงิน"
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp1),
        elevation = 4.dp1,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp1))
    ) {
        Column(modifier = Modifier.padding(16.dp1)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.size(40.dp1).background(Color.Gray)) {
                    Image(painter = painterResource(id = ), contentDescription = "Image")
                }
                Spacer(modifier = Modifier.width(8.dp1))
                Column {
                    Text(text = title, style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Location", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(4.dp1))
                    Text(text = "฿ $price", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.height(8.dp1))

            Text(text = label, style = MaterialTheme.typography.bodyMedium, color = when (status) {
                "ยกเลิก" -> Color.Red
                "คืนเงิน" -> Color.Green
                "ดำเนินการสำเร็จ" -> Color.Green
                "ที่ต้องรีวิว" -> Color.Orange
                else -> Color.Blue
            })

            Spacer(modifier = Modifier.height(8.dp1))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { /*  */ }, colors = ButtonDefaults.run { buttonColors(Color.Blue) }) {
                    Text(buttonText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OrderScreen()
   
}


