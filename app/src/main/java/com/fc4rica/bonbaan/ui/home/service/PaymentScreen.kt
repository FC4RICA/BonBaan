package com.fc4rica.bonbaan.ui.home.service

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.fc4rica.bonbaan.ui.components.BackNavBar
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.utils.saveImageToGallery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@SuppressLint("SuspiciousIndentation")
@Composable
fun PaymentScreen(
    onBackClick: () -> Unit,
    onCompleted: (String) -> Unit,
    viewModel: PaymentViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.startPollingPaymentStatus()
    }

    LaunchedEffect(state.isPaid) {
        if (state.isPaid && state.order != null)
            onCompleted(state.order!!.id)
    }

    fun downloadQRImage() {
        CoroutineScope(Dispatchers.IO).launch {
            saveImageToGallery(
                context,
                state.order?.transaction?.charge?.metadata!!
            )
        }
    }

    Scaffold(
        topBar = {
            BackNavBar(
                onBackClick = onBackClick,
                content = {
                    Text(
                        text = "รอการชำระเงิน",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
        ) {
            Text(
                text = "฿ ${state.order!!.price} บาท",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            if (state.order?.transaction?.charge?.expiresAt != null) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "ภายในวันที่ ${
                        state.order?.transaction?.charge?.expiresAt?.format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        )
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(Modifier.height(16.dp))

            // QR
            AsyncImage(
                model = state.order?.transaction?.charge?.metadata,
                contentDescription = "Promptpay QR code",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Spacer(Modifier.height(16.dp))
            BonBaanButton(
                text = "บันทึกคิวอาร์โค้ด",
                onClick = { downloadQRImage() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(16.dp))
            Text(
                text = "วิธีชำระเงินด้วย QR พร้อมเพย์",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "1. กด \"บันทึกคิวอาร์โค้ด\" หรือถ่ายภาพหน้าจอคิวอาร์โค้ด\n2. เปิดแอปพลิเคชันธนาคารแล้วเลือกจ่ายด้วย QR พร้อมเพย์",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}