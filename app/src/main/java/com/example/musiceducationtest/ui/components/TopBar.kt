package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.viewmodel.LessonViewModel

// トップバー
@Composable
fun TopBar(navController: NavController, lessonViewModel: LessonViewModel) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val selectedLesson by lessonViewModel.selectedLesson.collectAsState()

    Row(
        modifier = Modifier
            .background(Color(0xFFEEEEEE))
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentRoute == "loginScreen" || currentRoute == "newRegistrationScreen") {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "もどる")
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = getTitleForRoute(currentRoute),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Spacer(modifier = Modifier.weight(1f))

        if (currentRoute == "lessonSelectionScreen") {
            IconButton(onClick = { navController.navigate("loginScreen") }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "ログイン",
                        tint = Purple500,
                        modifier = Modifier
                            .size(40.dp)
                    )
                    Text(
                        text = "ログイン",
                        fontSize = 12.sp,
                    )
                }
            }
        }

        if (currentRoute != "lessonSelectionScreen"
            && currentRoute != "loginScreen"
            && currentRoute != "newRegistrationScreen"
        ) { // 問題選択画面では非表示
            Text(text = selectedLesson?.title ?: "レッスン0")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = selectedLesson?.songTitle ?: "曲が選択されていません。")
        }

        Spacer(modifier = Modifier.width(20.dp))
    }
}


fun getTitleForRoute(route: String?): String {
    return when (route) {
        "lessonSelectionScreen" -> "レッスンを えらんで スタート"
        "loginScreen" -> "ログイン"
        "newRegistrationScreen" -> "アカウントを さくせい"
        "explanationScreen" -> "せつめい"
        "explanationScreen2" -> "がめんの せつめい"
        "songCompositionScreen" -> "しょうせつを ならびかえて もとの曲を さいげん しましょう。"
        else -> "エラー：画面が選択されていません。"
    }
}