package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Teal200
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musiceducationtest.viewmodel.BottomBarViewModel
import com.example.musiceducationtest.viewmodel.LessonManagerViewModel
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel

// ボトムバー
@Composable
fun BottomBar(navController: NavController) {
    val bottomBarViewModel: BottomBarViewModel = viewModel()
    val musicPlayerViewModel: MusicPlayerViewModel = viewModel()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showDialog by bottomBarViewModel.showDialog.collectAsState()

    musicPlayerViewModel.initializeMediaPlayer()

    Row(
        modifier = Modifier.background(Color(0xFFEEEEEE)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // やめるボタン
        BottomNavigateButton(
            imageVector = Icons.Default.Clear,
            label = "やめる",
            backgroundColor = Color(0xFF424242),
            enabled = true,
            onClick = {
                bottomBarViewModel.toggleDialog(true) // 終了確認のダイアログを表示
                musicPlayerViewModel.releaseMediaPlayer()
            }
        )

        // 空間を埋める
        Spacer(modifier = Modifier.weight(1f))

        // 音楽プレイヤー
        BottomMusicPlayer(viewModel = musicPlayerViewModel)

        // 空間を埋める
        Spacer(modifier = Modifier.weight(1f))

        // もどるボタン
        BottomNavigateButton(
            imageVector = Icons.Default.KeyboardArrowLeft,
            label = "もどる",
            backgroundColor = Teal200,
            enabled = currentRoute == "songComposition/{lessonId}",
            onClick = {
                navController.navigateUp()
                musicPlayerViewModel.releaseMediaPlayer()
            }
        )

        // つぎへボタン
        BottomNavigateButton(
            imageVector = Icons.Default.KeyboardArrowRight,
            label = "つぎへ",
            backgroundColor = Purple500,
            enabled = currentRoute == "explanation/{lessonId}",
            onClick = {
                val lessonId = navController.currentBackStackEntry?.arguments?.getString("lessonId")
                navController.navigate("songComposition/$lessonId")
                musicPlayerViewModel.releaseMediaPlayer()
            }
        )
    }

    // ダイアログの表示（やめるボタンを押した時の処理）
    if (showDialog) {
        ConfirmExitDialog(navController, bottomBarViewModel)
    }
}