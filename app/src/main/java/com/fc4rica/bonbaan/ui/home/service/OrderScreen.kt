package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.BonBaanTextField
import com.fc4rica.bonbaan.ui.components.DatePickerInputField
import com.fc4rica.bonbaan.ui.components.TextArea
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrderScreen(
    onSubmitOrder: () -> Unit,
    onBack: () -> Unit,
    viewModel: OrderViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isSubmitSuccess) {
        if (state.isSubmitSuccess) {
            onSubmitOrder()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BackNavBar(
                onBackClick = onBack,
                content = {
                    Text(
                        text = "คำสั่งซื้อ",
                        style = MaterialTheme.typography.titleLarge,
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("ราคาทั้งหมด")
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = if (state.selectedPackage != null && state.selectedPackage?.price!! > 0.0) "฿ ${state.selectedPackage?.price}" else "ยังไม่กำหนด",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    BonBaanButton(
                        text = "ยืนยันคำสั่งซื้อ",
                        onClick = {
                            if (state.isVow) viewModel.submitVowOrder()
                            else if (state.isFulfill) viewModel.submitFulfillOrder()
                        },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }
    ) { innerPadding ->

        // Package
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 24.dp)
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
                    onPackageSelected = { viewModel.updateSelectedPackage(it) }
                )

                if (state.selectedPackage != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    if (state.selectedPackage?.id!!.isEmpty()) {
                        TextArea(
                            value = state.customItem,
                            onValueChange = { viewModel.updateCustomItem(it) },
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
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Other Detail
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "รายละเอียดเพิ่มเติม",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Vow
                if (state.isVow) {
                    TextArea(
                        value = state.vow,
                        onValueChange = { viewModel.updateVow(it) },
                        label = "คำขอในการบนบาน"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("ขอบเขตวันที่ที่ต้องการให้คำขอสำเร็จ")
                    Spacer(modifier = Modifier.height(4.dp))
                    DatePickerInputField(onDateSelected = { viewModel.updateDeadline(it ?: "") })

                    Spacer(modifier = Modifier.height(16.dp))
                }

                TextArea(
                    value = state.note,
                    onValueChange = { viewModel.updateNote(it) },
                    label = "บันทึกเตือนความจำ"
                )

                // Fulfill
                if (state.isFulfill && state.fulfilledVowRecord != null) {
                    Text(
                        text = "รายละเอียดการบนบาน",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))

                    if (state.vowRecords.count() > 1) {
                        VowRecordOption(
                            vowRecords = state.vowRecords,
                            selectedVowRecords = state.fulfilledVowRecord,
                            onVowRecordSelected = {
                                viewModel.updateFulfillVowRecord(it)
                            }
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "\"${state.fulfilledVowRecord!!.vow}\"",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "บนบานเมื่อ ${
                                state.fulfilledVowRecord!!.createdAt.format(
                                    DateTimeFormatter.ofPattern(
                                        "dd/MM/yyyy"
                                    )
                                )
                            }",
                            softWrap = false
                        )
                        Text(
                            text = "คุณขอให้สำเร็จภายในวันที่ ${
                                state.fulfilledVowRecord!!.deadline.format(
                                    DateTimeFormatter.ofPattern(
                                        "dd/MM/yyyy"
                                    )
                                )
                            }",
                            softWrap = false
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(8.dp))

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VowRecordOption(
    vowRecords: List<VowRecord>,
    selectedVowRecords: VowRecord?,
    onVowRecordSelected: (VowRecord) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        BonBaanTextField(
            readOnly = true,
            value = selectedVowRecords?.vow ?: "เลือกการบนบาน",
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
            vowRecords.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            "${item.vow} (${
                                item.createdAt.format(
                                    DateTimeFormatter.ofPattern(
                                        "(dd/MM/yyyy)"
                                    )
                                )
                            })"
                        )
                    },
                    onClick = {
                        onVowRecordSelected(item)
                        expanded = false
                    },
                )
            }
        }
    }
}