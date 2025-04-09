package com.fc4rica.bonbaan.ui.home.feed

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.components.BonBaanButton


import com.fc4rica.bonbaan.ui.components.ButtonVariant

data class OrderDetail(
    val serviceName: String,
    val location: String,
    val nameSurname: String,
    val wish: String,
    val dueDate: String,
    val packageName: String,
    val price: Int,
    val items: List<String>,
)

data class PackageItem(
    val id: String,
    val name: String,
    val price: Int,
    val items: String
)


@Composable
fun OrderSummaryScreen() {
    var isSelected by remember { mutableStateOf(true) }

    val mockOrder = OrderDetail(
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
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 30.dp, top = 16.dp, end = 30.dp, bottom = 100.dp)
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
                        .padding(start = 0.dp)
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
                    text = "สรุปรายการสั่งซื้อ",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

        Column {
            Column (modifier = Modifier
                .padding(top=8.dp,bottom = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(7.dp))
                .background(MaterialTheme.colorScheme.surface)

            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                    Image(
                        painter = painterResource(id = R.drawable.logo1),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(7.dp)
                            )
                            .height(100.dp)
                            .width(100.dp)
                            .padding(12.dp,end = 8.dp)

                    )
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = mockOrder.serviceName,style = MaterialTheme.typography.titleMedium,fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = "Location",
                                modifier = Modifier.size(14.dp),
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = mockOrder.location,style = MaterialTheme.typography.bodySmall) }

                    }}
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Text(
                    text = "ข้อมูลการบนบาน",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mockOrder.nameSurname,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "คำขอในการบนบาน",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mockOrder.wish,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "สำเร็จภายในวันที่",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mockOrder.dueDate,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
            PackageCard(
                name = mockOrder.packageName,
                price = mockOrder.price,
                items = mockOrder.items,
                onClickDetail = { }
            )
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Text(
                    text ="ช่องทางการชำระเงิน",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    RadioButton(
                        selected = true,
                        onClick = null,
                        enabled = false
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "พร้อมเพย์",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }


            }
        Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 30.dp, vertical = 16.dp)
                ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "฿ ${mockOrder.price}",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )

                BonBaanButton(modifier = Modifier.width(200.dp),
                    text = "ยืนยันคำสั่งซื้อ",
                    onClick = { },
                    variant = ButtonVariant.SECONDARY
                )
            }
        }

    }

}



@Composable
fun PackageCard(
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
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium
                )
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
                    Text(
                        text = "- $item",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSummaryScreen() {
    OrderSummaryScreen(
    )

}