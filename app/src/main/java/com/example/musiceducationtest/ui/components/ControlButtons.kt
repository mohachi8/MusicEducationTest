package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musiceducationtest.ui.theme.Shapes
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun ControlButtons(
    lessonViewModel: LessonViewModel,
    songCompositionViewModel: SongCompositionViewModel,
    navController: NavController,
) {
    val lesson by lessonViewModel.selectedLesson.collectAsState()
    val isCorrectOrder by songCompositionViewModel.isCorrectOrder.collectAsState() // 正解かどうか
    val showDialog by songCompositionViewModel.showDialog.collectAsState() // ダイアログの表示・非表示の状態

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .height(320.dp)
            .clip(Shapes.small)
            .background(Color.Gray)
            .padding(10.dp)
    ) {
        // 「えらぶ」ボタン
        Button(
            onClick = {
                songCompositionViewModel.addToFlowChart() // 選択されたブロックをフローチャートに追加
            },
            modifier = Modifier.width(100.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "えらぶ",
                    //modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "えらぶ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }

        // 「１つもどす」ボタン
        Button(
            onClick = {
                songCompositionViewModel.removeFromFlowChart() // フローチャートのリストからブロックを一つ削除
            },
            modifier = Modifier.width(100.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Undo,
                    contentDescription = "１つもどす",
                    //modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "１つもどす",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // 「リセット」ボタン
        Button(
            onClick = {
                songCompositionViewModel.initializeFlowChart(lesson?.id ?: "") // 現在のレッスンに基づいた初期化を行う
            },
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier.width(100.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "リセット",
                    tint = Color.White,
                    //modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "リセット",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // 「答えあわせ」ボタン
        Button(
            onClick = {
                songCompositionViewModel.checkAnswer(lesson?.id ?: "")
                songCompositionViewModel.toggleDialog(true)
            },
            modifier = Modifier.width(100.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Checklist,
                    contentDescription = "答えあわせ",
                    tint = Color.White
                )
                Text(
                    text = "答えあわせ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }




        // 答えあわせのダイアログ
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { songCompositionViewModel.toggleDialog(false) },
                title = { Text("こたえあわせ") },
                text = {
                    // 答えあわせの結果を表示
                    if (isCorrectOrder != null) {
                        if (isCorrectOrder == true) {
                            // 正解の場合の表示
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "正解",
                                    tint = Color.Green
                                )
                                Text("せいかい！", color = Color.Green)
                            }
                        } else {
                            // 不正解の場合の表示
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.SentimentVeryDissatisfied,
                                    contentDescription = "不正解",
                                    tint = Color.Red
                                )
                                Text("まちがっています．もういちど かんがえてみよう．", color = Color.Red)
                            }
                        }
                    }
                },
                // 正解していた時のみ，次の画面に遷移できるようにする．
                confirmButton = {
                    if (isCorrectOrder == true) {
                        TextButton(
                            onClick = {
                                songCompositionViewModel.toggleDialog(false)
                                navController.navigate("lessonSelectionScreen")
                            }
                        ) {
                            Text("おわる")
                        }
                    }
                },
                // キャンセルの処理
                dismissButton = {
                    TextButton(
                        onClick = {
                            songCompositionViewModel.toggleDialog(false)
                        }
                    ) {
                        Text("もどる")
                    }
                }
            )
        }
    }
}