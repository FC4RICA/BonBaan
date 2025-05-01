package com.fc4rica.bonbaan.ui.home.service

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.DatePickerInputField
import com.fc4rica.bonbaan.ui.components.DropdownSelector
import com.fc4rica.bonbaan.ui.components.TextArea
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrderScreen(
    onSubmitOrder: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: OrderViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSubmitSuccess) {
        if (state.isSubmitSuccess) {
            onSubmitOrder()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BackNavBar(
                onBackClick = onBackClick,
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
            // Package
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

                DropdownSelector(
                    label = "เลือกแพ็คเกจ",
                    options = state.packages,
                    selectedOption = state.selectedPackage,
                    onOptionSelected = { viewModel.updateSelectedPackage(it) },
                    optionToString = { "${it.name} (${it.price} บาท)" }
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

                if (!state.packageError.isNullOrEmpty()) {
                    (Modifier.height(2.dp))
                    Text(
                        text = state.packageError ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
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
                    if (!state.vowError.isNullOrEmpty()) {
                        (Modifier.height(2.dp))
                        Text(
                            text = state.vowError ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("ขอบเขตวันที่ที่ต้องการให้คำขอสำเร็จ")
                    Spacer(modifier = Modifier.height(4.dp))
                    DatePickerInputField(onDateSelected = { viewModel.updateDeadline(it ?: "") })
                    if (!state.deadlineError.isNullOrEmpty()) {
                        (Modifier.height(2.dp))
                        Text(
                            text = state.deadlineError ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextArea(
                        value = state.note,
                        onValueChange = { viewModel.updateNote(it) },
                        label = "บันทึกเตือนความจำ"
                    )
                }

                // Fulfill
                if (state.isFulfill && state.fulfilledVowRecord != null) {
                    Text(
                        text = "รายละเอียดการบนบาน",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))

                    if (state.vowRecords.count() > 1) {
                        DropdownSelector(
                            label = "เลือกการบนบาน",
                            options = state.vowRecords,
                            selectedOption = state.fulfilledVowRecord,
                            onOptionSelected = { viewModel.updateFulfillVowRecord(it) },
                            optionToString = {
                                "${it.vow} (${it.createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))})"
                            }
                        )
                    }
                    if (!state.vowRecordError.isNullOrEmpty()) {
                        (Modifier.height(2.dp))
                        Text(
                            text = state.vowRecordError ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
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