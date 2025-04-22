package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.OrderType
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.BonBaanTextField
import com.fc4rica.bonbaan.ui.components.DatePickerInputField
import com.fc4rica.bonbaan.ui.components.TextArea
import org.koin.androidx.compose.koinViewModel

private data class OrderState(
    val packages: List<Package> = listOf(
        Package("1", "คนคุยทันใจ", "", 100.0, listOf("ธูป"), OrderType("", "")),
        Package("2", "แฟนดีไม่่หนีไม่จ่าย", "", 200.0, listOf("เทียน"), OrderType("", "")),
        Package("3", "อิอิซ่า", "", 300.0, listOf("ปลาร้า"), OrderType("", "")),
        Package("", "แพ็กเกจบนบานแบบกำหนดเอง", "", 0.0, listOf(""), OrderType("", ""))
    ),
    var selectedPackage: Package? = null,
    val isVow: Boolean = true,
    val isFulfill: Boolean = false,
    var vow: String = "",
    var note: String = "",
    val deadline: String = "",
    val customItem: String = "",
    val errorMessage: String? = null
)

@Composable
fun OrderScreen(
    onSubmitOrder: () -> Unit,
    onBack: () -> Unit,
//    viewModel: OrderViewModel = koinViewModel()
) {
    val state = OrderState()
//    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = onBack,
                content = {
                    Text(
                        text = "คำสั่งซื้อ",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
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
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("ราคาทั้งหมด")
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = if (state.selectedPackage != null) "฿ ${state.selectedPackage?.price}" else "฿ ~",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    BonBaanButton(
                        text = "ยืนยันคำสั่งซื้อ",
                        onClick = { },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 30.dp, vertical = 16.dp)
                .imePadding()
        ) {
            Text(
                text = "แพ็กเกจ",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            PackageOption(
                packages = state.packages,
                selectedPackage = state.selectedPackage,
                onPackageSelected = { state.selectedPackage = it } // updateSelectedPackage(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (state.selectedPackage != null) {
                if (state.selectedPackage?.id!!.isEmpty()) {
                    TextArea(
                        value = state.customItem,
                        onValueChange = { }, // updateCustomItem(it) },
                        label = "รายการสินค้าที่คุณต้องการให้เราจัดหา",
                    )
                } else {
                    Text("รายการสินค้าในแพ็กเกจ")
                    Spacer(Modifier.height(8.dp))
                    Text(
                        state.selectedPackage!!.items.joinToString("\n"),
                        Modifier.padding(start = 16.dp)
                    )
                }
            }



            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "รายละเอียดเพิ่มเติม",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (state.isVow) {
                TextArea(
                    value = state.vow,
                    onValueChange = { state.vow = it }, // updateVow(it) } ,
                    label = "คำขอในการบนบาน"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("ขอบเขตวันที่ที่ต้องการให้คำขอสำเร็จ")
                Spacer(modifier = Modifier.height(4.dp))
                DatePickerInputField(onDateSelected = { state.deadline })

                Spacer(modifier = Modifier.height(16.dp))
            }

            TextArea(
                value = state.note,
                onValueChange = { state.note = it }, // updateNote(it) },
                label = "บันทึกเตือนความจำ"
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageOption(
    packages: List<Package>,
    selectedPackage: Package?,
    onPackageSelected: (Package) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        BonBaanTextField(
            readOnly = true,
            value = selectedPackage?.name ?: "เลือกแพ็คเกจ",
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .fillMaxWidth(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(8.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainer)
        ) {
            packages.forEach { item ->
                DropdownMenuItem(
                    text = { Text("${item.name} (${item.price} บาท)") },
                    onClick = {
                        onPackageSelected(item)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderScreen() {
    OrderScreen({}, {})

}