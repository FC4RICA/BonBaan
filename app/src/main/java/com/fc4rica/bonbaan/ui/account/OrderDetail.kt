package com.fc4rica.bonbaan.ui.account

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OrderDetailScreen(status: String) {
    val label = when (status) {
        "ยกเลิก" -> "การยกเลิก"
        "คืนเงิน" -> "การคืนเงิน"
        "ดำเนินการสำเร็จ" -> "การดำเนินการเสร็จสิ้น"
        "รอการชำระเงิน" -> "รอการชำระเงิน"
        "อยู่ระหว่างการตรวจสอบ" -> "อยู่ระหว่างการตรวจสอบ"
        else -> "สถานะไม่รู้จัก"
    }

    val buttonText = when (status) {
        "ยกเลิก" -> "ดูคำการยกเลิก"
        "คืนเงิน" -> "ซื้ออีกครั้ง"
        "ดำเนินการสำเร็จ" -> "ซื้ออีกครั้ง"
        "รอการชำระเงิน" -> "ชำระเงิน"
        "อยู่ระหว่างการตรวจสอบ" -> "รอการตรวจสอบ"
        else -> "ดูรายละเอียด"
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* handle back action */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "รายละเอียดการสั่งซื้อ", style = MaterialTheme.typography.h6)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OrderItem(title = "ดำเนินการสำเร็จ", price = 89, status = status)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* */ }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)) {
            Text(buttonText)
        }
    }
}

@Composable
fun OrderItem(title: String, price: Int, status: String) {
    val label = when (status) {
        "ยกเลิก" -> "ยกเลิก"
        "คืนเงิน" -> "คืนเงิน"
        "ดำเนินการสำเร็จ" -> "ดำเนินการสำเร็จ"
        "รอการชำระเงิน" -> "รอการชำระเงิน"
        "อยู่ระหว่างการตรวจสอบ" -> "อยู่ระหว่างการตรวจสอบ"
        else -> "ที่ต้องชำระ"
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                // Image placeholder
                Box(modifier = Modifier.size(40.dp).background(Color.Gray)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Image")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = title, style = MaterialTheme.typography.body1)
                    Text(text = "Location", style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "฿ $price", style = MaterialTheme.typography.body1)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Status Label
            Text(text = label, style = MaterialTheme.typography.body2, color = when (status) {
                "ยกเลิก" -> Color.Red
                "คืนเงิน" -> Color.Green
                "ดำเนินการสำเร็จ" -> Color.Green
                "รอการชำระเงิน" -> Color.Orange
                "อยู่ระหว่างการตรวจสอบ" -> Color.Gray
                else -> Color.Blue
            })

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OrderDetailScreen()
}
