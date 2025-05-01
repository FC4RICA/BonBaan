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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fc4rica.bonbaan.domain.model.OrderStatus
import com.fc4rica.bonbaan.ui.home.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onClickProfile: () -> Unit,
    onClickOrderStatuses: () -> Unit,
    onClickOrderStatus: (String) -> Unit,
    onClickReviews: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(bottom = 1.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "บัญชี",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            // Profile
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.size(64.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center)
                            .border(width = 2.dp, color = Color.Gray, CircleShape)
                            .padding(8.dp)
                    )

                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClickProfile() }
                            .align(Alignment.BottomEnd)
                            .background(MaterialTheme.colorScheme.surface, CircleShape)
                            .padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = state.user?.username ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Spacer(Modifier.height(8.dp))

            // Status
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "รายการสั่งซื้อ",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "ดูคำสั่งซื้อทั้งหมด",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.clickable { onClickOrderStatuses() })
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val pending = state.ordersCountByStatus.entries.first { it.key.name == OrderStatus.Pending.engName }
                    StatusIconButton(
                        icon = Icons.AutoMirrored.Outlined.ReceiptLong,
                        label = "รอรับออเดอร์",
                        onClick = { onClickOrderStatus(pending.key.id) },
                        count = pending.value
                    )
                    val unpaid = state.ordersCountByStatus.entries.first { it.key.name == OrderStatus.Unpaid.engName }
                    StatusIconButton(
                        icon = Icons.Outlined.AccountBalanceWallet,
                        label = "ที่ต้องชำระ",
                        onClick = { onClickOrderStatus(unpaid.key.id) },
                        count = unpaid.value
                    )
                    val processing = state.ordersCountByStatus.entries.first { it.key.name == OrderStatus.Processing.engName }
                    StatusIconButton(
                        icon = Icons.Outlined.HourglassEmpty,
                        label = "กำลังดำเนินการ",
                        onClick = { onClickOrderStatus(processing.key.id) },
                        count = processing.value
                    )
                    val confirm = state.ordersCountByStatus.entries.first { it.key.name == OrderStatus.Confirm.engName}
                    StatusIconButton(
                        icon = Icons.Outlined.CheckCircle,
                        label = "ที่ต้องยืนยัน",
                        onClick = { onClickOrderStatus(confirm.key.id) },
                        count = confirm.value
                    )
                    val review = state.ordersCountByStatus.entries.first { it.key.name == OrderStatus.Review.engName }
                    StatusIconButton(
                        icon = Icons.Outlined.RateReview,
                        label = "ที่ต้องรีวิว",
                        onClick = { onClickOrderStatus(review.key.id) },
                        count = review.value
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Review
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .clickable { onClickReviews() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "รายการรีวิวของฉัน", style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun StatusIconButton(
    icon: ImageVector,
    label: String,
    count: Int = 0,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box {
            Icon(
                icon,
                contentDescription = label,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(28.dp)
            )
            if (count > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (8).dp, y = (-4).dp)
                        .size(18.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(100)),
                ) {
                    Text(
                        text = count.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = label, style = MaterialTheme.typography.labelSmall, softWrap = false)
    }
}