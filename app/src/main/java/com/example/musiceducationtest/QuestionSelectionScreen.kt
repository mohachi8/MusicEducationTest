package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musiceducationtest.ui.theme.Purple200

@Composable
fun QuestionSelectionScreen(navController: NavController) {

    val lessons = listOf(
        Lesson("レッスン1", "きらきら星"),
        Lesson("レッスン2", "うみはひろいな"),
        Lesson("レッスン3", "かっこう")
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .padding(start = 100.dp, end = 100.dp, top = 80.dp)
    ) {
        items(lessons) { lesson ->
            LessonBox(
                lesson = lesson,
                lessonId = lessons.indexOf(lesson),
                navController = navController
            )
        }
    }
}

@Composable
fun LessonBox(lesson: Lesson, lessonId: Int, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp)
            .aspectRatio(1f)
            .background(Purple200)
            .clickable { navController.navigate("explanation/$lessonId") }
            .border(width = 8.dp, color = Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = lesson.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = lesson.description,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

// レッスンのデータクラス
data class Lesson(val title: String, val description: String)
