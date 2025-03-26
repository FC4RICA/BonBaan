package com.fc4rica.bonbaan.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.fillMaxWidth(),
            isEnabled = selectedInterests.isNotEmpty() // Disable button if no interest is selected
        )
    }
}

@Composable
fun OnboardingHeader(title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Bonbaan Logo",
            tint = Color.Unspecified,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun InterestSelection(
    interests: List<Pair<String, Int>>,
    selectedInterests: Set<String>,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        interests.forEach { (label, iconRes) ->
            FilterChip(label, iconRes, selectedInterests.contains(label)) {
                onSelect(label)
            }
        }
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
