package com.example.musiceducationtest.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Teal200
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musiceducationtest.R
import com.example.musiceducationtest.viewmodel.BottomBarViewModel
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel

// ボトムバー
@Composable
fun BottomBar(
    navController: NavController,
    lessonViewModel: LessonViewModel,
    bottomBarViewModel: BottomBarViewModel,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showDialog by bottomBarViewModel.showDialog.collectAsState()
    val selectedLesson by lessonViewModel.selectedLesson.collectAsState()

    // レッスンの変更を検知して、音楽プレイヤーを初期化
    LaunchedEffect(selectedLesson) {
        if (selectedLesson != null) {
            musicPlayerViewModel.initializeMediaPlayer(
                musicResId = selectedLesson?.musicResId ?: R.raw.twinkle_twinkle_little_star
            )
        }
    }

    // ダイアログの表示（やめるボタンを押した時の処理）
    if (showDialog) {
        ConfirmExitDialog(navController, bottomBarViewModel, musicPlayerViewModel)
    }

    Row(
        modifier = Modifier.background(Color(0xFFEEEEEE)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // やめるボタン
        BottomNavigateButton(
            imageVector = Icons.Default.Logout,
            label = "やめる",
            backgroundColor = Color(0xFF424242),
            enabled = true,
            onClick = {
                bottomBarViewModel.toggleDialog(true) // 終了確認のダイアログを表示
            }
        )

        // 空間を埋める
        Spacer(modifier = Modifier.weight(1f))

        // 音楽プレイヤー
        MusicPlayer(viewModel = musicPlayerViewModel)

        // 空間を埋める
        Spacer(modifier = Modifier.weight(0.7f))

        // もどるボタン
        BottomNavigateButton(
            imageVector = Icons.Default.ArrowBackIosNew,
            label = "もどる",
            backgroundColor = Teal200,
            enabled = currentRoute == "songCompositionScreen",
            onClick = {
                navController.navigateUp()
            }
        )

        // すすむボタン
        BottomNavigateButton(
            imageVector = Icons.Default.ArrowForwardIos,
            label = "すすむ",
            backgroundColor = Purple500,
            enabled = currentRoute == "explanationScreen",
            onClick = {
                navController.navigate("songCompositionScreen")
            }
        )
    }
}