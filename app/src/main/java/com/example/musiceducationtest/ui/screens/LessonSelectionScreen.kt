package com.example.musiceducationtest.ui.screens

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
import com.example.musiceducationtest.ui.components.LessonBox
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
            // レッスンを配置
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