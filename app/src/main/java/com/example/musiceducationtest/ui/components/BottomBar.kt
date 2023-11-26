package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Teal200
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musiceducationtest.BottomMusicPlayer
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel

// ボトムバー
@Composable
fun BottomBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var showDialog: Boolean by remember { mutableStateOf(false) }


    // Composable関数内でViewModelを取得
    val musicPlayerViewModel = viewModel<MusicPlayerViewModel>()

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
            onClick = { showDialog = true }
        )

        Spacer(modifier = Modifier.weight(1f))

        // 音楽プレイヤー
        BottomMusicPlayer(viewModel = musicPlayerViewModel)

        Spacer(modifier = Modifier.weight(1f))

        // もどるボタン
        BottomNavigateButton(
            imageVector = Icons.Default.KeyboardArrowLeft,
            label = "もどる",
            backgroundColor = Teal200,
            enabled = currentRoute == "songComposition/{lessonId}",
            onClick = { navController.navigateUp() }
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
            }
        )
    }

    // ダイアログの表示
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "本当に終了しますか？",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "学習内容は保存されません。",
                    fontSize = 16.sp,
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // 終了処理
                        showDialog = false
                        musicPlayerViewModel.stopMusic()
                        navController.navigate("lessonSelection")
                    }
                ) {
                    Text("終了")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("キャンセル")
                }
            }
        )
    }
}

// ボトムナビゲーションバーボタン
@Composable
fun BottomNavigateButton(
    imageVector: ImageVector,
    label: String,
    backgroundColor: Color,
    enabled: Boolean, // ボタンが有効かどうか
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(if (enabled) backgroundColor else Color.Gray)
            .clickable(enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = if (enabled) Color.White else Color.LightGray,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = label,
                color = if (enabled) Color.White else Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
