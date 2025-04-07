package com.fc4rica.bonbaan.ui.home.feed

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.ui.components.BonBaanButton

import com.fc4rica.bonbaan.ui.components.BonBaanTextField
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun OrderFormScreen() {
    var vowDetail by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 30.dp, top = 16.dp, end = 30.dp, bottom = 100.dp) // เผื่อปุ่มล่าง
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "คำสั่งซื้อ",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }



            BonBaanTextField(label = "ชื่อจริง",
                value = "Name",
                onValueChange = { })
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(4.dp))

            BonBaanTextField(label = "นามสกุล",
                value = "Surname",
                onValueChange = { })

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "คำขอในการบนบานของคุณ", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            BigTextField(value = vowDetail,
                onValueChange = { vowDetail = it })

            Spacer(modifier = Modifier.height(16.dp))
            DatePickerSection()

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "บันทึกเพิ่มเติมเพื่อเตือนความจำ", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            BigTextField(value = vowDetail, onValueChange = { vowDetail = it })

            Spacer(modifier = Modifier.height(16.dp))
        }


        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            BonBaanButton(
                text = "ยืนยันคำสั่งซื้อ",
                onClick = { },
                variant = ButtonVariant.SECONDARY
            )
        }
    }
}


@Composable
fun DatePickerSection() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    var selectedDateText by remember { mutableStateOf("") }

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
                text = if (selectedDateText.isEmpty()) "เลือกวันที่" else selectedDateText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Composable
fun BigTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormScreen() {
        OrderFormScreen(
        )

}