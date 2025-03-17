package com.fc4rica.bonbaan.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.components.*
import com.fc4rica.bonbaan.ui.theme.BonBaanTheme

@Composable
fun InterestScreen(navController: NavController) {
    var selectedInterests by remember { mutableStateOf(setOf<String>()) }

    val interests = listOf(
        "การเรียน" to R.drawable.ic_learning,
        "การงาน" to R.drawable.ic_work,
        "ความรัก" to R.drawable.ic_love,
        "ครอบครัว" to R.drawable.ic_family,
        "สุขภาพ" to R.drawable.ic_health,
        "โชคลาภ" to R.drawable.ic_luck,
        "การเดินทาง" to R.drawable.ic_travel
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingHeader(title = "เรื่องที่สนใจ", subtitle = "เลือกด้านที่คุณสนใจในการบนน")

        Spacer(modifier = Modifier.height(16.dp))

        InterestSelection(interests, selectedInterests) { interest ->
            selectedInterests = if (selectedInterests.contains(interest)) {
                selectedInterests - interest
            } else {
                selectedInterests + interest
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        BonBaanButton(
            text = "ตกลง",
            onClick = { navController.navigate("nextScreen") },
            variant = ButtonVariant.PRIMARY,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInterestScreen() {
    val navController = rememberNavController()
    BonBaanTheme {
        InterestScreen(navController)
    }
}
