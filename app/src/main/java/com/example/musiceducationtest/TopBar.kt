package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TopBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Row(
        modifier = Modifier
            .background(Color(0xFFEEEEEE))
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = getTitleForRoute(currentRoute),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

fun getTitleForRoute(route: String?): String {
    return when (route) {
        "questionSelection" -> "問題選択してください。"
        "explanation/{problemId}" -> "音楽を聞きましょう"
        "songComposition/{problemId}" -> "ブロックを ならびかえて もとの曲を さいげん しましょう。"
        else -> "アプリ"
    }
}