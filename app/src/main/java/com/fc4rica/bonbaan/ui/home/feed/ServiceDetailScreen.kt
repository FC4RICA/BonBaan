package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R

import com.fc4rica.bonbaan.ui.components.BonBaanButton

import com.fc4rica.bonbaan.ui.components.ButtonVariant

enum class Choice { Vow, Fullfill }

data class ReviewItem(
    val rating: Int,
    val comment: String
)

data class PackageItem(
    val name: String,
    val price: String,
    val detailItems: List<String>,
    val reviewItems:List<ReviewItem>
)

@Composable
fun ServiceDetailScreen (serviceId: String) {
    var selectedChoice by remember { mutableStateOf(Choice.Vow) }

    val vowPackageList = listOf(
        PackageItem("ชุดคนคุยเร่นๆ",
            "฿ 39",
            listOf("ดอกไม้ 1 ดอก", "ธูป 1 ดอก", "เทียน 1 คู่"),
            listOf(
                ReviewItem(5, "มะกี้เหงามาก อยู่ดีๆก้อมีคนชวรคุย"),
                ReviewItem(4, "ก็ดี"),
                ReviewItem(2, "ขอไป 5 นาทีแร้ว ไหนอะไม่เหรมีคัยมาคุยเรย")
            )),
        PackageItem("ชุดแฟนไม่หนี แถมฟรีความสุข", "฿ 89", listOf("ดอกกุหลาบสีแดง 9 ดอก", "ธูปแดง 9 ดอก", "เทียน 1 คู่", "น้ำแดง 1 ขวด"),listOf(
            ReviewItem(5, "มะกี้ทะเลาะกับแฟน ตอนนี้ดีกันระ"),
            ReviewItem(4, "ก็ดี"),
            ReviewItem(2, "ขอไป 5 นาทีแร้ว แฟนมะเหนรักเรย แง")
        )),
        PackageItem("ชุดเนื้อคู่ด่วน", "฿ 159", listOf("ดอกไม้ 12 ดอก", "น้ำเขียว 1 ขวด", "เทียน 1 คู่"),listOf(
            ReviewItem(5, "ขอเมื่อกี้ ตอนนี้มีแฟนระ"),
            ReviewItem(4, "ก็ดี"),
            ReviewItem(2, "ขอไป 5 นาทีแร้ว ไหนอะแฟร")
        ))
    )

    val fulfillPackageList = listOf(
        PackageItem("ชุดขอบคุณเทพ", "฿ 59", listOf("น้ำแดง 1 ขวด", "พวงมาลัย 1 พวง"),listOf(
            ReviewItem(5, "ได้ตามบรีฟเรย ขอบคุรจร้า"),
            ReviewItem(4, "ขอบคุนงับ"),
            ReviewItem(2, "สมหวังอย่ แต่ช้าอ้ะ"))),
        PackageItem("ชุดแก้บนจัดเต็ม", "฿ 3999", listOf("นางรำ 50 คน", "วงดนตรีไทย"),listOf(
            ReviewItem(5, "ได้ตามบรีฟเรย ขอบคุรจร้า"),
            ReviewItem(4, "ก็ดี แต่ช้าไปนิสน้า"),
            ReviewItem(2, "ช้ามาก")
    )))

    val packageList = when (selectedChoice) {
        Choice.Vow -> vowPackageList
        Choice.Fullfill -> fulfillPackageList
    }

    var selectedPackage by remember { mutableStateOf(packageList.first()) }
    LaunchedEffect(selectedChoice) {
        selectedPackage = packageList.first()
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    MaterialTheme.colorScheme.primary
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .align(Alignment.TopStart)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            
    }
        Spacer(modifier = Modifier.height(12.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) { Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("$serviceId",style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.Bold)
            Text("Price")
        }

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier.size(14.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Location")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Text("Interest")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("รายละเอียด",style = MaterialTheme.typography.titleMedium,fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            Text("พระตรีมูรติเป็นเทพแห่งความรักและสมหวังที่ผู้คนเคารพบูชา โดยเฉพาะในเรื่องความรัก การงาน และโชคลาภ การบนบานนิยมใช้ดอกกุหลาบแดง ธูปแดง 9 ดอก และเทียนแดง 1 คู่ พิธีมักทำวันพฤหัสบดี เวลา 21:30 น. ซึ่งเชื่อว่าเป็นเวลาศักดิ์สิทธิ์ในการขอพรให้สำเร็จผล")
            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                BonBaanButton(
                    text = "บนบาน",
                    onClick = { selectedChoice = Choice.Vow },
                    variant = if (selectedChoice == Choice.Vow) ButtonVariant.PRIMARY else ButtonVariant.TEXT
                )
                BonBaanButton(
                    text = "แก้บน",
                    onClick = { selectedChoice = Choice.Fullfill },
                    variant = if (selectedChoice == Choice.Fullfill) ButtonVariant.PRIMARY else ButtonVariant.TEXT
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("แพ็คเกจสินค้า",style = MaterialTheme.typography.titleMedium,fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))


            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                packageList.forEach { pack ->
                    Package(
                        name = pack.name,
                        price = pack.price,
                        detailItem = pack.detailItems,
                        reviewItem = pack.reviewItems,
                        onClick = { selectedPackage = pack },
                        isSelected = selectedPackage.name == pack.name
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("ข้อมูลรายละเอียดแพ็คเกจ", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                selectedPackage.detailItems.forEach { item ->
                    Text("• $item", style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) { Text("คะแนนการรีวิว",style = MaterialTheme.typography.titleMedium,fontWeight = FontWeight.Bold)
                    Text("Link to คะแนนทั้งหมด")
                }
                selectedPackage.reviewItems.forEach { review ->
                    Review(reviewItem = review)
                }
            } }


}
}

@Composable
fun Review(reviewItem: ReviewItem) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
        Row {
            repeat(5) { index ->
                Text(
                    text = if (index < reviewItem.rating) "★" else "☆",
                    color = if (index < reviewItem.rating) Color(0xFF8B00FF) else Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = reviewItem.comment, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}



@Composable
fun Package(
    name: String,
    price: String,
    detailItem: List<String>,
    reviewItem: List<ReviewItem>,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .then(Modifier
                .padding(end = 4.dp))
            .clickable { onClick() }
    ) {
        Text(text = name, color = Color.White)
        Text(text = price, fontWeight = FontWeight.Bold, color = Color.White)
    }
}


@Preview(showBackground = true)
@Composable
fun ServicePreview() {
    ServiceDetailScreen("พระตรี")

}