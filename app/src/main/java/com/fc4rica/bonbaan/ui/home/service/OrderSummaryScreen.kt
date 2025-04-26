package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fc4rica.bonbaan.domain.model.Attachment
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.OrderType
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class OrderSummaryState(
    val vowOrderRequest: VowOrderRequest? = // null,
        VowOrderRequest(
        serviceId = "1",
        packageId = "1",
        vow = "I wish for ...",
        deadline = "2024/12/19",
        price = 0.0,
        note = "service note",
        items = listOf("item 1"),
        orderTypeID = "",
    ),
    val fulfillOrderRequest: FulfillOrderRequest? = null,
//        FulfillOrderRequest(
//        serviceId = "1",
//        packageId = "1",
//        price = 0.0,
//        items = listOf("item 3 unit", "item 10 unit"),
//        orderTypeID = "",
//        vowRecordID = "1"
//    ),
    val service: Service? = Service(
        id = "1",
        name = "service name",
        address = "location 1",
        description = "",
        attachments = listOf(
            Attachment(
                id = "",
                url = "https://picsum.photos/200"
            )
        ),
        rate = 0.0,
    ),
    val user: User? = User(
        id = "",
        username = "",
        firstname = "John",
        lastname = "Doe",
        phone = "",
        email = "",
    ),
    val packageItem: Package? = //null,
        Package(
            id = "1",
            name = "package name",
            description = "package description 101",
            price = 300.0,
            items = listOf("item 3 unit", "item 10 unit"),
            orderType = OrderType("", "")
        ),
    val vowRecord: VowRecord? = VowRecord(
        id = "",
        vow = "qwer wer sdfsefwsdfwsefse4fs dfgsegsfdgarhaerhg erfgerghadfaedfpikjwerionbo",
        deadline = LocalDateTime.now(),
        note = "notesefwsdgvsdf fhsuefhsioeuf soeiufjhsoieufhaosdjnvoajsdnv",
        createdAt = LocalDateTime.now(),
        vowOrder = Order(
            id = "2iv73nge-94kdf829-fm39fk3p",
            price = 0.0,
            items = emptyList(),
            packageItem = null,
            createdAt = LocalDateTime.now(),
            transaction = null,
            cancellationReason = null,
            status = Status("", ""),
            attachments = emptyList(),
            service = null,
        )
    ),
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null
)

@Composable
fun OrderSummaryScreen() {
    val state = OrderSummaryState()

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = { },
                content = {
                    Text(
                        text = "สรุปรายการสั่งซื้อ",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(top = 1.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("ราคาทั้งหมด")
                        Spacer(Modifier.width(4.dp))
                        val price = (state.vowOrderRequest?.price
                            ?: state.fulfillOrderRequest?.price).toString()
                        Text(
                            text = if (price.isNotEmpty() && price.toFloat() > 0.0) "฿ $price" else "ยังไม่กำหนด",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    BonBaanButton(
                        text = "ยืนยันคำสั่งซื้อ",
                        onClick = {},
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .verticalScroll(rememberScrollState())
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 0.dp
                )
                .imePadding()
        ) {
            // Service
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 12.dp, horizontal = 24.dp)
            ) {
                AsyncImage(
                    model = state.service?.attachments?.first(),
                    contentDescription = "Service Image",
                    modifier = Modifier
                        .size(82.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = state.service?.name ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.size(14.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = state.service?.address ?: "",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Order
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                // Vow
                if (state.vowOrderRequest != null) {
                    Text(
                        text = "ข้อมูลการบนบาน",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "${state.user?.firstname} ${state.user?.lastname}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = state.vowOrderRequest.vow,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "สำเร็จภายในวันที่",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = state.vowOrderRequest.deadline,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    if (state.vowOrderRequest.note.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "บันทึกเพิ่มเติม",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = state.vowOrderRequest.note,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // Fulfill
                if (state.fulfillOrderRequest != null && state.vowRecord != null) {
                    Text(
                        text = "ข้อมูลการบนที่คุณต้องการแก้",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "เมื่อวันที่ ${
                            state.vowRecord.createdAt.format(
                                DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy"
                                )
                            )
                        }",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = state.vowRecord.vow,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "ขอให้สำเร็จภายในวันที่ ${
                            state.vowRecord.deadline.format(
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            )
                        }",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // Package and Payment
            if (state.packageItem != null) {
                // Package
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    PackageCard(state.packageItem)
                }

                Spacer(Modifier.height(8.dp))

                // Payment
                if (!state.vowOrderRequest?.packageId.isNullOrEmpty() || !state.fulfillOrderRequest?.packageId.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(vertical = 16.dp, horizontal = 24.dp)
                    ) {
                        Text(
                            text = "ช่องทางการชำระเงิน",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

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
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "พร้อมเพย์",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))
            }

            // Custom Item
            if (state.packageItem == null) {
                val requestItem = state.vowOrderRequest?.items ?: state.fulfillOrderRequest?.items
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    Text(
                        text = "รายการสินค้าที่คุณต้องการ",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = requestItem?.joinToString("\n") ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun PackageCard(
    packageItem: Package,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "แพ็คเกจ",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = packageItem.name,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "฿ ${packageItem.price}  บาท", style = MaterialTheme.typography.bodyMedium)
        }

        if (isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            packageItem.items.forEach { item ->
                Text(
                    text = "- $item",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "รายละเอียดเพิ่มเติม",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Color.Gray
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSummaryScreen() {
    OrderSummaryScreen(
    )

}