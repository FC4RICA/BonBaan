package com.fc4rica.bonbaan.ui.home.profile

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.OrderStatus
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.model.toOrderStatus
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersStatusScreen(
    onBackClick: () -> Unit,
    onClickOrderDetail: (String) -> Unit,
    onClickServiceDetail: (String) -> Unit,
    onClickPayment: (String) -> Unit,
    onClickReview: (String) -> Unit,
    viewModel: OrdersStatusViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = onBackClick,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "รายการสั่งซื้อ",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        StatusSelector(
                            options = state.status,
                            selectedOption = state.selectedStatus,
                            onOptionSelected = { viewModel.filterOrdersByStatus(it.id) },
                            optionToString = { it.toOrderStatus()?.displayName ?: it.name }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { }
            items(state.orders) { order ->
                OrderCard(
                    order = order,
                    onClickDetail = onClickOrderDetail,
                    onClickPayment = onClickPayment,
                    onClickServiceDetail = onClickServiceDetail,
                    onClickReview = onClickReview,
                )
            }
            item { }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusSelector(
    options: List<Status>,
    selectedOption: Status?,
    onOptionSelected: (Status) -> Unit,
    optionToString: (Status) -> String
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .border(1.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(8.dp))
        ) {
            Box(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                Text(
                    text = selectedOption?.let(optionToString) ?: "ทั้งหมด",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(modifier = Modifier.padding(8.dp)) {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(8.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainer)
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = optionToString(item),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        onOptionSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun OrderCard(
    order: Order,
    onClickDetail: (String) -> Unit,
    onClickPayment: (String) -> Unit,
    onClickServiceDetail: (String) -> Unit,
    onClickReview: (String) -> Unit
) {
    val status = order.status.toOrderStatus()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClickDetail(order.id) }
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = order.service?.attachments?.firstOrNull()?.url,
                contentDescription = "service image",
                modifier = Modifier
                    .size(82.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = order.service?.name ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = status?.displayName ?: "",
                        color = status?.color ?: Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = order.service?.address ?: "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "ราคา", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "฿ ${order.price} บาท", fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .align(Alignment.End)
        ) {
            when (status) {
                OrderStatus.Pending -> {
                    BonBaanButton(
                        text = "คุณจะได้รับการตรวจสอบภายใน 24 ชั่วโมง",
                        onClick = { },
                        isEnabled = false
                    )
                }

                OrderStatus.Unpaid -> {
                    BonBaanButton(
                        text = "ชำระเงิน",
                        onClick = { onClickPayment(order.id) },
                        variant = ButtonVariant.PRIMARY
                    )
                }

                OrderStatus.Processing -> {
                    BonBaanButton(
                        text = "ดูสถานะการทำงาน",
                        onClick = { onClickDetail(order.id) },
                        variant = ButtonVariant.OUTLINED
                    )
                }

                OrderStatus.Confirm, OrderStatus.Approve -> {
                    BonBaanButton(
                        text = "ดูหลักฐานการทำงาน",
                        onClick = { onClickDetail(order.id) },
                        variant = ButtonVariant.OUTLINED
                    )
                }

                OrderStatus.Review -> {
                    BonBaanButton(
                        text = "รีวิวบริการ",
                        onClick = { onClickReview(order.id) },
                        variant = ButtonVariant.OUTLINED
                    )
                }

                OrderStatus.Completed, OrderStatus.Refund, OrderStatus.Cancel -> {
                    BonBaanButton(
                        text = "ซื้ออีกครั้ง",
                        onClick = { onClickServiceDetail(order.id) },
                        variant = ButtonVariant.OUTLINED
                    )
                }

                else -> {}
            }
        }
    }
}