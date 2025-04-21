package com.fc4rica.bonbaan.ui.home.service

import android.app.DatePickerDialog
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.TextArea
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class PackageItems(
    val id: String,
    val name: String,
    val price: Int,
    val items: String
)


@Composable
fun OrderFormScreen() {
    val initialPackageId = "2"
    var vowDetail by remember { mutableStateOf("") }

    val packages = listOf(
        PackageItems("1", "คนคุยทันใจ", 100, "ธูป"),
        PackageItems("2", "แฟนดีไม่่หนีไม่จ่าย", 200, "เทียน"),
        PackageItems("3", "อิอิซ่า", 300, "ปลาร้า")
    )


    var selectedPackage by remember {
        mutableStateOf(packages.find { it.id == initialPackageId })
    }

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = { },
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
                            text = "฿ ${selectedPackage?.price}",
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
        ) {
            Text(text = "แพ็คเกจ", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            PackageOption(
                packages = packages,
                selectedPackage = selectedPackage,
                onPackageSelected = { selectedPackage = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedPackage != null) {
                TextArea(
                    value = selectedPackage!!.items,
                    onValueChange = { },
                    label = "รายการสินค้าในแพ็คเกจ",
                    readOnly = true
                )
            }



            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "รายละเอียดเพิ่มเติม", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            TextArea(
                value = vowDetail,
                onValueChange = { vowDetail = it },
                label = "คำขอในการบนบาน"
            )

            Spacer(modifier = Modifier.height(16.dp))
            DatePickerSection()

            Spacer(modifier = Modifier.height(16.dp))

            TextArea(
                value = vowDetail,
                onValueChange = { vowDetail = it },
                label = "บันทึกเตือนความจำ"
            )
        }
    }
}


@Composable
fun DatePickerSection() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance().apply {
        add(Calendar.DAY_OF_YEAR, 1)
    }
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    var selectedDateText by remember {
        mutableStateOf(dateFormat.format(calendar.time))
    }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDateText = dateFormat.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column {
        Text(
            text = "คุณต้องการให้คำขอของคุณสำเร็จภายในเวลา",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
                .clickable { datePickerDialog.show() }
        ) {
            Text(
                text = selectedDateText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageOption(
    packages: List<PackageItems>,
    selectedPackage: PackageItems?,
    onPackageSelected: (PackageItems) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = selectedPackage?.name ?: "เลือกแพ็คเกจ",
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            packages.forEach { item ->
                DropdownMenuItem(
                    text = { Text("${item.name} (${item.price} บาท)") },
                    onClick = {
                        onPackageSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormScreen() {
    OrderFormScreen(
    )

}