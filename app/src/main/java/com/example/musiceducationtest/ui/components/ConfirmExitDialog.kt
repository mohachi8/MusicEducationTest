package com.example.musiceducationtest.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musiceducationtest.viewmodel.BottomBarViewModel
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel

@Composable
fun ConfirmExitDialog(
    navController: NavController,
    bottomBarViewModel: BottomBarViewModel,
    musicPlayerViewModel:MusicPlayerViewModel
) {
    AlertDialog(
        onDismissRequest = {
            // ダイアログの外側をタップした時の処理
            bottomBarViewModel.toggleDialog(false)
        },
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
                    // 音楽プレイヤーのリソースを解放
                    musicPlayerViewModel.releaseMediaPlayer()
                    // 終了の処理
                    navController.navigate("lessonSelectionScreen")
                    bottomBarViewModel.toggleDialog(false)
                }
            ) {
                Text("終了")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    // キャンセルの処理
                    bottomBarViewModel.toggleDialog(false)
                }
            ) {
                Text("キャンセル")
            }
        }
    )
}