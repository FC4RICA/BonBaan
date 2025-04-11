package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.components.SearchBox

@Composable
fun SearchScreen() {
    var searchValue by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SearchBox(searchValue = searchValue, onValueChange = { searchValue = it })
        SearchHistory()
        Recommend()
    }

}

@Composable
fun SearchHistory(){
    Column(modifier = Modifier
        .padding(top = 12.dp)
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.surface)) {
        Text(text = "ประวัติการค้นหา",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium,color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold)
        HistoryItem("วัดฟ้าประทาน")
        HistoryItem("วัดดอยคำ ยำอาฟเตอร์ยู")
        HistoryItem("วัดดูยูมีน ไอดอนโน บัดไอเลิฟยู")
    }

}

@Composable
fun HistoryItem(name: String){
    Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = name,style = MaterialTheme.typography.bodyMedium)
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier.size(14.dp),

                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
    }
}

@Composable
fun Recommend(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp, bottom = 12.dp)
        .background(MaterialTheme.colorScheme.surface)) {
        Text(text = "แนะนำ", modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
            ,color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold)
        RecommendItem("วัดฟ้าประทาน")
        RecommendItem("วัดดอยคำ ยำอาฟเตอร์ยู")
        RecommendItem("วัดดูยูมีน ไอดอนโน บัดไอเลิฟยู")
    }
}

@Composable
fun RecommendItem(name: String){
    Column (modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(7.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)

    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo",
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(7.dp)
                    )
                    .height(100.dp)
                    .width(100.dp)
                    .padding(12.dp)

            )
            Column {
                Text(text = name,style = MaterialTheme.typography.titleSmall,fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier.size(14.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Location",style = MaterialTheme.typography.bodySmall) }

                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "หมวดหมู่",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }


            }

        }



    }

}