package com.fc4rica.bonbaan.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ArrowBack
import com.fc4rica.bonbaan.ui.components.BonBaanTextField
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fc4rica.bonbaan.R

@Composable
fun SearchScreen(){
    var searchValue by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBEBEB))
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SearchBar(searchValue = searchValue, onValueChange = { searchValue = it })
        SearchHistory()
        Recommend()
    }

}

@Composable
fun SearchBar(searchValue: String, onValueChange: (String) -> Unit){
    Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color(0xFF5E17EB)),
    contentAlignment = Alignment.Center
) {
        Row( modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))

            BonBaanTextField(
                label = "ค้นหา",
                value = searchValue,
                onValueChange = onValueChange
            )

            TextField(
                value = "",
                onValueChange = {},

                placeholder = { Text("ค้นหา") },
                modifier = Modifier.background(Color(0xFF5E17EB))

                    .height(38.dp)
                    .fillMaxWidth(0.9f)
                    .clip(
                        RoundedCornerShape(7.dp),
                        ))
        }
}
}

@Composable
fun SearchHistory(){
    Column(modifier = Modifier
        .padding(top = 12.dp)
        .fillMaxWidth()
        .background(color = Color.White)) {
        Text(text = "ประวัติการค้นหา",
            modifier = Modifier.padding(16.dp),
            fontSize = 17.sp,color = Color(0xFF5E17EB),
            fontWeight = FontWeight.Bold)
        HistoryItem("วัดฟ้าประทาน")
        HistoryItem("วัดดอยคำ ยำอาฟเตอร์ยู")
        HistoryItem("วัดดูยูมีน ไอดอนโน บัดไอเลิฟยู")
    }

}

@Composable
fun HistoryItem(Name: String){
    Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "$Name")
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier.size(14.dp),

                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@Composable
fun Recommend(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp, bottom = 12.dp)
        .background(color = Color.White)) {
        Text(text = "แนะนำ", modifier = Modifier.padding(16.dp), fontSize = 17.sp,color = Color(0xFF5E17EB),fontWeight = FontWeight.Bold)
        RecommendItem("วัดฟ้าประทาน")
        RecommendItem("วัดดอยคำ ยำอาฟเตอร์ยู")
        RecommendItem("วัดดูยูมีน ไอดอนโน บัดไอเลิฟยู")
    }
}

@Composable
fun RecommendItem(Name: String){
    Column (modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(7.dp))
        .background(Color(0xFFEBEBEB))

    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo",
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(7.dp))
                    .height(100.dp)
                    .width(100.dp)
                    .padding(12.dp)

            )
            Column (){
                Text(text = "$Name", fontSize = 17.sp,fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier.size(14.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Location") }

                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF5E17EB))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "หมวดหมู่",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


            }

            }



    }

}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    SearchScreen()
}