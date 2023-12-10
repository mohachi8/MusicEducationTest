package com.example.musiceducationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musiceducationtest.ui.theme.MusicEducationTestTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musiceducationtest.ui.components.BottomBar
import com.example.musiceducationtest.ui.components.TopBar
import com.example.musiceducationtest.ui.screens.ExplanationScreen
import com.example.musiceducationtest.ui.screens.LessonSelectionScreen
import com.example.musiceducationtest.ui.screens.SongCompositionScreen
import com.example.musiceducationtest.viewmodel.BottomBarViewModel
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Jetpack Compose を使ってUIを記述
            MusicEducationTestTheme { // アプリケーションのテーマ
                val lessonViewModel: LessonViewModel = hiltViewModel()
                val bottomBarViewModel: BottomBarViewModel = hiltViewModel()
                val musicPlayerViewModel: MusicPlayerViewModel = hiltViewModel()
                val songCompositionViewModel: SongCompositionViewModel = hiltViewModel()

                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopBar(navController, lessonViewModel) }, // トップバー
                        bottomBar = { // ボトムバー
                            if (currentRoute != "lessonSelectionScreen") { // 問題選択画面では非表示
                                BottomBar(
                                    navController,
                                    lessonViewModel,
                                    bottomBarViewModel,
                                    musicPlayerViewModel
                                )
                            }
                        }
                    ) { innerPadding -> // メインコンテンツ
                        NavHost( // ナビゲーションの管理
                            navController,
                            startDestination = "lessonSelectionScreen", // 最初に表示される画面
                            Modifier.padding(innerPadding)
                        ) {
                            // 問題選択画面
                            composable("lessonSelectionScreen") {
                                LessonSelectionScreen(navController, lessonViewModel)
                            }
                            // 問題説明画面
                            composable("explanationScreen") {
                                ExplanationScreen(lessonViewModel)
                            }
                            // 学習画面
                            composable("songCompositionScreen") {
                                SongCompositionScreen(lessonViewModel, songCompositionViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}