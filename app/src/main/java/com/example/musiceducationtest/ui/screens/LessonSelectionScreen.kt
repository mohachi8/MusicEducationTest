package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.musiceducationtest.ui.components.LessonBox
import com.example.musiceducationtest.viewmodel.LessonManagerViewModel

@Composable
fun LessonSelectionScreen(navController: NavController) {
    val lessonViewModel: LessonManagerViewModel = hiltViewModel()
    // 全てのレッスンを取得
    val lessons = lessonViewModel.allLessons

    Column() {
        Spacer(modifier = Modifier.height(100.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(250.dp),
            contentPadding = PaddingValues(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = with(LocalDensity.current) {
                    (LocalConfiguration.current.screenWidthDp.dp.toPx() * 0.05f).toDp()
                })
        ) {
            // レッスンを配置
            items(lessons) { lesson ->
                LessonBox(
                    lesson = lesson,
                    lessonViewModel = lessonViewModel,
                    navController = navController
                )
            }
        }
    }
}