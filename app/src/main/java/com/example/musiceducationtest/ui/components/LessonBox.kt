package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musiceducationtest.model.LessonDataModel
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.viewmodel.LessonManagerViewModel

// 選択肢のBox
@Composable
fun LessonBox(
    lesson: LessonDataModel,
    lessonViewModel: LessonManagerViewModel,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .aspectRatio(1f)
            .background(Purple200)
            .clickable {
                // 選択肢したレッスンをViewModelで保持
                lessonViewModel.selectLesson(lesson.id)
                // レッスンを選択した時、レッスンのIDをナビゲーションの引数として渡し、画面遷移
                navController.navigate("explanation/${lesson.id}")
            }
            .border(width = 6.dp, color = Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // レッスンタイトル（例：レッスン1、レッスン2...）
            Text(
                text = lesson.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            // レッスンで使用する曲名（例：きらきら星、海...）
            Text(
                text = lesson.songTitle,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}