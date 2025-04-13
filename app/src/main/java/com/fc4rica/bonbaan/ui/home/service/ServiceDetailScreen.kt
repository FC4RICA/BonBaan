package com.fc4rica.bonbaan.ui.home.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant

import com.fc4rica.bonbaan.utils.CategoryUtils

enum class Choice { Vow, Fullfill }

data class StateData (
    val service: Service = Service(
        id = "123",
        name = "Service title",
        description = "service description",
        rate = 3.6,
        address = "KMUTT",
        categories = listOf(),
        packages = listOf(),
        attachments = listOf(),
        reviews = listOf(),
    )
)

@Composable
fun ServiceDetailScreen () {
    val serviceCategory = Category("1", "ความรัก")
    val categoryWithIcon = CategoryUtils.mapCategoriesIcon(listOf(serviceCategory)).first()

    var selectedChoice by remember { mutableStateOf(Choice.Vow) }

    val state = StateData()

    val packageList = when (selectedChoice) {
        Choice.Vow -> state.service.packages.filter { it.orderType.name == "บนบาน" }
        Choice.Fullfill -> state.service.packages.filter { it.orderType.name == "แก้บน" }
    }

    var selectedPackage by remember { mutableStateOf(packageList.first()) }

    LaunchedEffect(selectedChoice) {
        selectedPackage = packageList.first()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column( 
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Title",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    categoryWithIcon.icon?.let { icon ->
                        Icon(
                            imageVector = icon,
                            contentDescription = categoryWithIcon.name,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = categoryWithIcon.name,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "รายละเอียด",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(state.service.description)
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ChoiceTab(
                        text = "บนบาน",
                        isSelected = selectedChoice == Choice.Vow,
                        onClick = { selectedChoice = Choice.Vow },
                        modifier = Modifier.weight(1f)
                    )
                    ChoiceTab(
                        text = "แก้บน",
                        isSelected = selectedChoice == Choice.Fullfill,
                        onClick = { selectedChoice = Choice.Fullfill },
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


                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    packageList.forEach { pack ->
                        PackageItem(
                            packageData = pack,
                            onClick = { selectedPackage = pack },
                            isSelected = selectedPackage.name == pack.name
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "ข้อมูลรายละเอียดแพ็คเกจ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column {
                    selectedPackage.items.forEach { item ->
                        Text("• $item", style = MaterialTheme.typography.bodySmall)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "คะแนนการรีวิว",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text("Link to คะแนนทั้งหมด")
                    }
                    state.service.reviews.map { review ->
                        ReviewItem(review)
                    }
                }

            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            BonBaanButton(
                text = "ซื้อเลย",
                onClick = { },
                variant = ButtonVariant.SECONDARY
            )
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
            .clickable {}
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
fun ReviewItem(review: Review) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
        Row {
            repeat(5) { index ->
                Text(
                    text = if (index < review.rating) "★" else "☆",
                    color = if (index < review.rating) Color(0xFF8B00FF) else Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = review.detail, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}



@Composable
fun PackageItem(
    packageData: Package,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .then(Modifier
                .padding(end = 4.dp))
            .clickable { onClick() }
    ) {
        Text(text = packageData.name, color = textColor)
        Text(text = packageData.price.toString(), fontWeight = FontWeight.Bold, color = textColor)
    }
}


@Preview(showBackground = true)
@Composable
fun ServicePreview() {
    ServiceDetailScreen()

}