package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton

@Composable
fun PaymentScreen() {
    val imageUrl =
        "https://www.blognone.com/sites/default/files/externals/255750d05125fccbe1d06e2b2ac1fa23.jpg"

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = {},
                content = { Text(
                    text = "รอการชำระเงิน",
                    style = MaterialTheme.typography.titleLarge,
                    ) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Promptpay QR code",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            )
            Spacer(Modifier.height(16.dp))
            BonBaanButton(
                text = "บันทึกคิวอาร์โค้ด",
                onClick = { },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(16.dp))
            Text(
                text = "วิธีชำระเงินด้วย QR พร้อมเพย์",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "1. กด \"บันทึกคิวอาร์โค้ด\" หรือถ่ายภาพหน้าจอคิวอาร์โค้ด\n2. เปิดแอปพลิเคชันธนาคารแล้วเลือกจ่ายด้วย QR พร้อมเพย์",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen()
}