package com.fc4rica.bonbaan.ui.home

import com.fc4rica.bonbaan.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.PaddingValues


@Composable
fun Homepage() {

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFEBEBEB))) {
        SearchBar()
        Spacer(modifier = Modifier.height(12.dp))
        Category()
        Spacer(modifier = Modifier.height(12.dp))
        Recommended()

    }

}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(0xFF5E17EB)),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = "",
            onValueChange = {},

            placeholder = { Text("ค้นหา") },
            modifier = Modifier.background(Color(0xFF5E17EB))

                .height(38.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(7.dp),


                ))

    }
}

@Composable
fun Category(){
    Column (modifier = Modifier.fillMaxWidth().background(color = Color.White)){
        Text(text = "หมวดหมู่", modifier = Modifier.padding(8.dp), fontSize = 17.sp,color = Color(0xFF5E17EB),fontWeight = FontWeight.Bold)
        Row (modifier = Modifier.horizontalScroll(rememberScrollState()).padding(8.dp)){
            SubCategory("ความรัก")
            SubCategory("การงาน")
            SubCategory("ค้าขาย")
            SubCategory("การเงิน")
            SubCategory("สุขภาพ")
    }
}
}

@Composable
fun Recommended(){
    val items = listOf(
        "Sevice1", "Sevice2", "Sevice3", "Sevice4"
    )
    Column (modifier = Modifier.fillMaxWidth().background(color = Color.White)){
        Text(text = "แนะนำ",modifier = Modifier.padding(8.dp), fontSize = 17.sp,color = Color(0xFF5E17EB),fontWeight = FontWeight.Bold)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // ✅ แถวละ 2 คอลัมน์
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp), // ✅ ระยะห่างแนวนอน
            verticalArrangement = Arrangement.spacedBy(8.dp) // ✅ ระยะห่างแนวตั้ง
        ) {
            items(items) { item ->
                RecommendationCard(title = item)
            }
        }

    }
}

@Composable
fun SubCategory(name: String){
    Column(modifier = Modifier.padding(top= 12.dp, start = 22.dp,end = 22.dp, bottom = 12.dp),horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier = Modifier.height(24.dp).width(24.dp).background(Color(0xFF5E17EB))){}
        Text(text = name)  }
}


@Composable
fun RecommendationCard(title: String) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(220.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.White)
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {

            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.temple),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)

                )


                Box(
                    modifier = Modifier
                        .background(Color(0xFF5E17EB), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        text = "หมวดหมู่",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row { Text(
                        text = "Rating ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                        Text(text = "icon")
                    }

                }

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(text = "icon")
                    Text(
                        text = " LocationName",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "฿Price",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Homepage()
}