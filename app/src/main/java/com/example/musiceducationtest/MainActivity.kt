package com.example.musiceducationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.example.musiceducationtest.ui.theme.MusicEducationTestTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Jetpack Compose を使ってUIを記述
            MusicEducationTestTheme { // アプリケーションのテーマ
                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopBar(navController) }, // トップバー
                        bottomBar = { // ボトムバー
                            if (currentRoute != "questionSelection") {
                                BottomNavigationBar(navController)
                            }
                        }
                    ) { innerPadding -> // メインコンテンツ
                        NavHost( // ナビゲーションの管理
                            navController,
                            startDestination = "questionSelection", // 最初に表示される画面
                            Modifier.padding(innerPadding)
                        ) {
                            // 問題選択画面
                            composable("questionSelection") { QuestionSelectionScreen(navController) }
                            // 問題説明画面。lessonIdによって表示される内容が切り替わる。
                            composable("explanation/{lessonId}") { backStackEntry ->
                                val lessonId = backStackEntry.arguments?.getString("lessonId")
                                    ?: "defaultLessonId" // デフォルト値
                                ExplanationScreen(lessonId, navController)
                            }
                            // 学習画面。lessonIdによって表示される内容が切り替わる。
                            composable("songComposition/{lessonId}") { backStackEntry ->
                                val lessonId = backStackEntry.arguments?.getString("lessonId")
                                    ?: "defaultLessonId" // デフォルト値
                                SongCompositionScreen(lessonId, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}