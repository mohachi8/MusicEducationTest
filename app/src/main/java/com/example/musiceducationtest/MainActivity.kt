package com.example.musiceducationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import com.example.musiceducationtest.ui.screens.LoginScreen
import com.example.musiceducationtest.ui.screens.NewRegistrationScreen
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
                        topBar = {// トップバー
//                            if (currentRoute != "loginScreen") {
                            TopBar(navController, lessonViewModel)
//                            }
                        },
                        bottomBar = { // ボトムバー
                            if (currentRoute != "lessonSelectionScreen"
                                && currentRoute != "loginScreen"
                                && currentRoute != "newRegistrationScreen"
                            ) { // 問題画面でのみ表示
                                BottomBar(
                                    navController,
                                    lessonViewModel,
                                    bottomBarViewModel,
                                    musicPlayerViewModel,
                                    songCompositionViewModel
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
                            composable(
                                route = "lessonSelectionScreen",
                                enterTransition = {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                        animationSpec = tween(700)
                                    )
                                },
                                exitTransition = {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                        animationSpec = tween(700)
                                    )
                                },
                                popEnterTransition = {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                        animationSpec = tween(700)
                                    )
                                },
                                popExitTransition = {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                        animationSpec = tween(700)
                                    )
                                }
                            ) {
                                LessonSelectionScreen(
                                    navController,
                                    lessonViewModel,
                                    songCompositionViewModel
                                )
                            }
                            // 問題説明画面
                            composable(
                                route = "explanationScreen",
                                enterTransition = {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                        animationSpec = tween(700)
                                    )
                                },
                                exitTransition = {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                        animationSpec = tween(700)
                                    )
                                },
                                popEnterTransition = {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                        animationSpec = tween(700)
                                    )
                                },
                                popExitTransition = {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                        animationSpec = tween(700)
                                    )
                                }
                            ) {
                                ExplanationScreen(lessonViewModel)
                            }
                            // 学習画面
                            composable(
                                route = "songCompositionScreen",
                                enterTransition = {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                        animationSpec = tween(700)
                                    )
                                },
                                exitTransition = {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                        animationSpec = tween(700)
                                    )
                                },
                                popEnterTransition = {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                        animationSpec = tween(700)
                                    )
                                },
                                popExitTransition = {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                        animationSpec = tween(700)
                                    )
                                }
                            ) {
                                SongCompositionScreen(lessonViewModel, songCompositionViewModel)
                            }
                            // ログイン画面
                            composable(
                                route = "loginScreen",
                            ) {
                                LoginScreen(navController)
                            }
                            // 新規登録画面
                            composable(
                                route = "newRegistrationScreen",
                            ) {
                                NewRegistrationScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}