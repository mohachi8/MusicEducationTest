package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.musiceducationtest.viewmodel.LessonManagerViewModel

@Composable
fun ExplanationScreen(lessonId: String, navController: NavController) {
    val lessonViewModel: LessonManagerViewModel = hiltViewModel()
    val lesson by lessonViewModel.selectedLesson.collectAsState()

    LaunchedEffect(lessonId) {
        lessonViewModel.selectLesson(lessonId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // それぞれのレッスンに対応した説明を表示
        Text(text = lesson?.description ?: "説明なし", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "下の音楽プレイヤーで音楽を聞きましょう。", fontSize = 20.sp)
    }
}