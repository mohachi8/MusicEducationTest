package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musiceducationtest.viewmodel.LessonViewModel

// トップバー
@Composable
fun TopBar(navController: NavController, lessonViewModel: LessonViewModel) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val selectedLesson by lessonViewModel.selectedLesson.collectAsState()

    Row(
        modifier = Modifier
            .background(Color(0xFFEEEEEE))
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = getTitleForRoute(currentRoute),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.weight(1f))

        if (currentRoute != "lessonSelectionScreen") { // 問題選択画面では非表示
            Text(text = selectedLesson?.title ?:"エラー")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = selectedLesson?.songTitle ?:"エラー")
        }
    }
}

fun getTitleForRoute(route: String?): String {
    return when (route) {
        "lessonSelectionScreen" -> "レッスンを えらんで 学習を スタート"
        "explanationScreen" -> "曲を きいてください。"
        "songCompositionScreen" -> "ブロックを ならびかえて もとの曲を さいげん しましょう。"
        else -> "エラー"
    }
}