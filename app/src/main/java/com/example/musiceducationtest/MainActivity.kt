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
        setContent {
            MusicEducationTestTheme {
                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopBar(navController) },
                        bottomBar = {
                            if (currentRoute != "questionSelection") {
                                BottomNavigationBar(navController)
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = "questionSelection",
                            Modifier.padding(innerPadding)
                        ) {
                            composable("questionSelection") { QuestionSelectionScreen(navController) }
                            composable("explanation/{lessonId}") { backStackEntry ->
                                val lessonId = backStackEntry.arguments?.getString("lessonId")
                                    ?: "defaultLessonId"
                                ExplanationScreen(lessonId, navController)
                            }
                            composable("songComposition/{lessonId}") { backStackEntry ->
                                val lessonId = backStackEntry.arguments?.getString("lessonId")
                                    ?: "defaultLessonId"
                                SongCompositionScreen(lessonId, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}