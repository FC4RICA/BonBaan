package com.fc4rica.bonbaan.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import com.fc4rica.bonbaan.ui.components.ButtonVariant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextDecoration
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.BonBaanTextField



@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF5E17EB)), contentAlignment = Alignment.Center){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text="เข้าสู่ระบบ", fontSize = 24.sp,color = Color.White,fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(40.dp))

            Text(text ="อีเมล",color = Color.White, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(12.dp))
            BonBaanTextField(label = "อีเมล", value = email, onValueChange = { email = it })


            Spacer(modifier = Modifier.height(16.dp))
            Text(text ="รหัสผ่าน",color = Color.White, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(12.dp))
            BonBaanTextField(label = "รหัสผ่าน", value = password, onValueChange = {password = it})

            Spacer(modifier = Modifier.height(35.dp))

            BonBaanButton(
                text = "เข้าสู่ระบบ",
                onClick = { },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(40.dp))
            Row{
                Text(text="ยังไม่มีบัญชีผู้ใช้? ",color = Color.White) //ไม่รู้จะทำยังไงให้มันเว้น
                Spacer(modifier = Modifier.height(8.dp))
                Text(text="สมัครเลย",color = Color.Yellow,textDecoration = TextDecoration.Underline)
            }

        }

    }

}
@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginScreen()
}
