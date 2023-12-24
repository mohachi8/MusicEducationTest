package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.R
import com.example.musiceducationtest.ui.components.BlockArea
import com.example.musiceducationtest.ui.components.ControlButtons
import com.example.musiceducationtest.ui.components.FlowChart
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Shapes
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun SongCompositionScreen(
    lessonViewModel: LessonViewModel,
    songCompositionViewModel: SongCompositionViewModel
) {
    val isPlaying =
        songCompositionViewModel.isPlayingFlowChart.collectAsState().value // フローチャートの曲が再生中かどうか

    Column(modifier = Modifier.padding(10.dp)) {

        // フローチャート表示エリア
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .clip(RoundedCornerShape(4.dp))
                .border(width = 2.dp, color = Color(0xFF424242), shape = RoundedCornerShape(4.dp))
                .padding(10.dp)
        ) {

            // ト音記号と拍子、曲の再生ボタン
            Box(
                modifier = Modifier
                    .size(80.dp, 160.dp)
            ) {
                // ト音記号と拍子
                Image(
                    painter = painterResource(id = R.drawable.star_treble_clef),
                    contentDescription = "TrebleClef",
                    modifier = Modifier.height(160.dp)
                )

                // 再生、停止ボタン
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter // 下部中央に配置
                ) {
                    IconButton(onClick = { songCompositionViewModel.startFlowChartMusic() }) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.StopCircle else Icons.Default.PlayCircle,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = Purple500,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                }
            }

            // フローチャート
            FlowChart(songCompositionViewModel)
        }

        // フローチャートと選択肢の間をめいいっぱい広げる
        Spacer(modifier = Modifier.weight(1f))

        // 選択肢エリア
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .clip(Shapes.small)
                .border(width = 2.dp, color = Color(0xFF424242), shape = Shapes.small)
        ) {
            // 操作ボタンエリア
            ControlButtons(lessonViewModel, songCompositionViewModel)

            // 選択肢
            Box(
                modifier = Modifier.weight(0.8f)
            ) { BlockArea(lessonViewModel, songCompositionViewModel) }
        }
    }
}