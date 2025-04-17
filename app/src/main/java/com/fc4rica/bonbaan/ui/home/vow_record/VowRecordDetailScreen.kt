package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant

data class vowDetail(
    val orderID:String,
    val serviceName: String,
    val location: String,
    val status:String,
    val nameSurname: String,
    val wish: String,
    val note:String,
    val dueDate: String,
    val packageName: String,
    val price: Int,
    val items: List<String>,
)




@Composable
fun VowRecordDetailScreen() {

    val mockOrder = vowDetail(
        orderID = "12345678",
        serviceName = "พระตรีมูรติ",
        location = "หน้าเซ็นทรัลเวิลด์",
        status = "แก้บนแล้ว",
        nameSurname = "อาบิตคำ ชาตรี",
        wish = """
        ไม่ขออะไรมาก ขอให้แฟนผมรักผม
    """.trimIndent(),
        note = "แฟนคนที่ 24",
        dueDate = "09 เมษายน 2568",
        packageName = "แฟนไม่หนี แถมฟรีความสุข",
        price = 89,
        items = listOf("ธูป 1 ชุด", "เทียน 1 ชุด", "น้ำแดง 1 ขวด")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
                    text = "บันทึกการบน",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Column {
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
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

                        Column(modifier = Modifier.padding(horizontal = 12.dp)) {

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
                            Spacer(modifier = Modifier.width(6.dp))
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
                                    text = mockOrder.status,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "\""+"${mockOrder.wish}"+"\"",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "วันที่บน : "+mockOrder.dueDate,style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "ขอบเขต : "+mockOrder.dueDate,style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "โน้ตของคุณ",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = mockOrder.note,
                        onValueChange = {},
                        readOnly = true,
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFEAEAEA)),
                        textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black),

                        )
                }
            }

            PackageDetail(
                orderid = mockOrder.orderID,
                name = mockOrder.packageName,
                price = mockOrder.price,
                items = mockOrder.items
            )


        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
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

}



@Composable
fun PackageDetail(orderid: String,
                  name: String,
                  price: Int,
                  items: List<String>,
) {

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
                text = "รายละเอียดคำสั่งซื้อ",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )


            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "หมายเลขคำสั่งซื้อ : $orderid",
                style = MaterialTheme.typography.bodyMedium,

                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ราคา : $price฿", fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "แพ็คเกจ : $name",
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
            items.forEach { item ->
                Text(
                    text = "- $item",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewRecordScreen() {
    VowRecordDetailScreen()

}