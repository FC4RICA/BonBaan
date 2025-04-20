package com.fc4rica.bonbaan.ui.home.service

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.ui.components.BackButton
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import com.fc4rica.bonbaan.ui.components.FilterChip
import com.fc4rica.bonbaan.ui.components.ImageCarousel
import com.fc4rica.bonbaan.ui.components.ReviewCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServiceDetailScreen(
    onOrderSuccess: () -> Unit,
    onBack: () -> Unit,
    viewModel: ServiceDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    val packageList = when (state.selectedOrderType) {
        PackageType.Vow -> state.packages.filter { it.orderType.name == PackageType.Vow.displayName }
        PackageType.Fulfill -> state.packages.filter { it.orderType.name == PackageType.Fulfill.displayName }
    }

    LaunchedEffect(state.isOrdering) {
        if (state.isOrdering) {
            onOrderSuccess()
            viewModel.resetOrdering()
        }
    }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 12.dp, horizontal = 24.dp),
            ) {
                BonBaanButton(
                    text = "ซื้อเลย",
                    onClick = { viewModel.createOrder() },
                    variant = ButtonVariant.SECONDARY,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    top = 0.dp,
                    bottom = it.calculateBottomPadding()
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary
                    ),
                contentAlignment = Alignment.Center
            ) {
                ImageCarousel(images = state.service.attachments)
                BackButton(
                    onClick = onBack,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart),
                    variant = ButtonVariant.SECONDARY
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 30.dp, end = 30.dp),
            ) {
                // Title
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.service.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = state.service.rate.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Location
                Row {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier.size(14.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(state.service.address)
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Category
                state.service.categories.map { category ->
                    FilterChip(
                        label = category.name,
                        icon = category.icon,
                        isSelected = false,
                        onClick = {})
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "รายละเอียด",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(state.service.description)

                Spacer(modifier = Modifier.height(24.dp))

                // Package
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ChoiceTab(
                        text = "บนบาน",
                        isSelected = state.selectedOrderType == PackageType.Vow,
                        onClick = { viewModel.updateSelectedOrderType(PackageType.Vow) },
                        modifier = Modifier.weight(1f)
                    )
                    ChoiceTab(
                        text = "แก้บน",
                        isSelected = state.selectedOrderType == PackageType.Fulfill,
                        onClick = { viewModel.updateSelectedOrderType(PackageType.Fulfill) },
                        modifier = Modifier.weight(1f)
                    )
                }


                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "แพ็คเกจสินค้า",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    packageList.forEach { pack ->
                        PackageCard(
                            packageData = pack,
                            onClick = { viewModel.updateSelectedPackageId(pack.id) },
                            isSelected = state.selectedPackageId == pack.id
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "ข้อมูลรายละเอียดแพ็คเกจ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    val selectedPackage = packageList.find { pack ->
                        pack.id == state.selectedPackageId && pack.orderType.name == state.selectedOrderType.displayName
                    }
                    Text(selectedPackage?.description ?: "", style = MaterialTheme.typography.bodyMedium)
                    selectedPackage?.items?.map { item ->
                        Text("• $item", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Review
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "รีวิว",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        BonBaanButton(
                            text = "ดูรีวิวทั้งหมด",
                            onClick = {},
                            variant = ButtonVariant.TEXT
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    state.service.reviews.map { review ->
                        ReviewCard(review)
                    }
                }
            }
        }
    }
}


@Composable
fun ChoiceTab(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val underlineColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    val textColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(bottom = 4.dp)
            .drawBehind {
                val strokeWidth = 4f
                val y = size.height
                drawLine(
                    color = underlineColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = textColor,
            fontWeight = fontWeight,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun PackageCard(
    packageData: Package,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    val textColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = packageData.name, color = textColor)
        Spacer(Modifier.height(6.dp))
        Text(text = packageData.price.toString(), color = textColor)
    }
}