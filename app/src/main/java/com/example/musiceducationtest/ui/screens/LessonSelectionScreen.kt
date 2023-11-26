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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.musiceducationtest.model.LessonDataModel
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.viewmodel.LessonManagerViewModel

@Composable
fun LessonSelectionScreen(navController: NavController) {

    val viewModel: LessonManagerViewModel = viewModel()
    val lessons = viewModel.allLessons


    Column() {
        Spacer(modifier = Modifier.height(100.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = with(LocalDensity.current) {
                    (LocalConfiguration.current.screenWidthDp.dp.toPx() * 0.05f).toDp()
                })
        ) {
            items(lessons) { lesson ->
                LessonBox(
                    lesson = lesson,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}

// 選択肢のBox
@Composable
fun LessonBox(lesson: LessonDataModel, viewModel: LessonManagerViewModel, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp)
            .aspectRatio(1f)
            .background(Purple200)
            .clickable {
                viewModel.selectLesson(lesson.id)
                navController.navigate("explanation/${lesson.id}") // レッスンを選択した時、レッスンのIDをナビゲーションの引数として渡す
            }
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
                text = lesson.songTitle,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}