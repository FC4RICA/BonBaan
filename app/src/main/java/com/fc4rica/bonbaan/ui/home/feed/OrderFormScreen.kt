package com.fc4rica.bonbaan.ui.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OrderFormScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row {  }
        Spacer(modifier = Modifier.height(12.dp))
        Category()
        Spacer(modifier = Modifier.height(12.dp))
        Recommended()

    }


}



@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
        OrderFormScreen(
        )

}