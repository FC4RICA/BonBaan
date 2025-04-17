package com.fc4rica.bonbaan.ui.home.service


import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant


data class vowRecord(
    val serviceName: String,
    val wish: String,
    val status: String,
    val dueDate: String?,
)


@Composable
fun VowRecordScreen() {

    val recordss = listOf(
        vowRecord(
            serviceName = "วัดหลวงพ่อโสธร",
            wish = "ขอ A นะนะนะ...",
            status = "ยังไม่ได้แก้บน",
            dueDate = "เหลืออีก 1 เดือน 2 วัน"
        ),
        vowRecord(
            serviceName = "วัดอรุณเบิกเนตรสยบมาราคา",
            wish = "ผมขอให้คนไทยบินได้",
            status = "หมดเวลา",
            dueDate = null
        ),
        vowRecord(
            serviceName = "พระตรีมูรติ",
            wish = "ขอให้บิทคอยทูเดอะมูน",
            status = "แก้บนแล้ว",
            dueDate = null
        )
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

                recordss.forEach { record ->
                    OrderStatusCard(order = record, onClick = {

                    })
                }
            }
        }
    }
}

@Composable
fun OrderStatusCard(
    order: vowRecord,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = order.serviceName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.status,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                text = order.wish,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

                order.dueDate?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }}


            if (order.status == "ยังไม่ได้แก้บน" || order.status == "หมดเวลา") {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    BonBaanButton(
                        text = "แก้บน",
                        onClick = onClick,
                        variant = ButtonVariant.PRIMARY
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewVowRecordScreen() {
    VowRecordScreen()

}