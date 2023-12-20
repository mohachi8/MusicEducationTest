package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun ControlButtons(
    lessonViewModel: LessonViewModel,
    songCompositionViewModel: SongCompositionViewModel
) {
    val lesson by lessonViewModel.selectedLesson.collectAsState()
    val isCorrectOrder by songCompositionViewModel.isCorrectOrder.collectAsState()
    val showDialog by songCompositionViewModel.showDialog.collectAsState()


    // ダイアログを表示
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { songCompositionViewModel.toggleDialog(false) },
            title = { Text("答えあわせ") },
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
                            Text("正解！", color = Color.Green)
                        }
                    } else {
                        // 不正解の場合の表示
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.SentimentVeryDissatisfied,
                                contentDescription = "不正解",
                                tint = Color.Red
                            )
                            Text("不正解！", color = Color.Red)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        songCompositionViewModel.toggleDialog(false)
                    }
                ) {
                    Text("とじる")
                }
            }
        )
    }


    Column(
        //verticalAlignment = Alignment.CenterVertically
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.height(320.dp)
    ) {
//        Text(
//            text = "ブロック エリア",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.ExtraBold,
//        )
//        Spacer(modifier = Modifier.width(20.dp))
        FlowChartMusicStartButton(songCompositionViewModel)


        // 「えらぶ」ボタン
        Button(onClick = {
            songCompositionViewModel.addToFlowChart()
        }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "えらぶ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "えらぶ"
                )
            }
        }

        //Spacer(modifier = Modifier.width(20.dp))

        // 「もどす」ボタン
        Button(onClick = {
            songCompositionViewModel.removeFromFlowChart()
        }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "もどす",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Undo,
                    contentDescription = "もどす"
                )
            }
        }
        //Spacer(modifier = Modifier.width(20.dp))

        // 「クリア」ボタン
        Button(
            onClick = {
                songCompositionViewModel.initializeFlowChart(lesson?.id ?: "")
            },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "クリア",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "クリア",
                    tint = Color.White
                )
            }
        }

        //Spacer(modifier = Modifier.weight(1f))
        //Spacer(modifier = Modifier.width(20.dp))

        // 「答えあわせ」ボタン
        Button(
            onClick = {
                songCompositionViewModel.checkAnswer(lesson?.id ?: "")
                songCompositionViewModel.toggleDialog(true)
            },
            colors = ButtonDefaults.buttonColors(Purple200)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "答えあわせ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Checklist,
                    contentDescription = "答えあわせ",
                    tint = Color.White
                )
            }
        }
    }
}