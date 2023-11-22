package com.example.musiceducationtest

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ExplanationScreen(problemId: String, navController: NavController) {
    Text(text = "下の音楽プレイヤーで音楽を聞きましょう。")
}