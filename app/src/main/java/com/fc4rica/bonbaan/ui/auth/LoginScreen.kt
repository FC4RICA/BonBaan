package com.fc4rica.bonbaan.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextDecoration


@Composable
fun LoginScreen(name: String) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF5E17EB)), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            Spacer(modifier = Modifier.height(20.dp))
//            Text(text = "Hello $name!")
            Text(text="เข้าสู่ระบบ", fontSize = 24.sp,color = Color.White,fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text ="อีเมลล์",color = Color.White, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.height(25.dp),
                value = email,
                onValueChange = { email = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text ="รหัสผ่าน",color = Color.White, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.height(25.dp),
                value = password,
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                onClick = { result = email },shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),

                modifier = Modifier
                    .height(55.dp)
                    .width(280.dp)
            ) {
                Text("เข้าสู่ระบบ",color = Color(0xFF5E17EB),fontWeight = FontWeight.Bold)
            }
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
    LoginScreen("สาวดนตรี")
}
