package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.components.BlockArea
import com.example.musiceducationtest.ui.components.ControlButtons
import com.example.musiceducationtest.ui.components.FlowChartMusicStartButton
import com.example.musiceducationtest.ui.components.SongCompositionFlowChart
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun SongCompositionScreen(
    lessonViewModel: LessonViewModel,
    songCompositionViewModel: SongCompositionViewModel
) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {

        Spacer(modifier = Modifier.height(10.dp))

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
/*
            // 「曲を再生」ボタン
            FlowChartMusicStartButton(songCompositionViewModel)

            Spacer(modifier = Modifier.width(10.dp))

            // 縦区切り線
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .width(1.dp)
                    .background(Color(0xFF424242))
            )
            Spacer(modifier = Modifier.width(10.dp))


 */
            // フローチャート
            SongCompositionFlowChart(songCompositionViewModel)
        }

        Spacer(modifier = Modifier.weight(1f))

        // 選択肢エリア
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .clip(RoundedCornerShape(4.dp))
                .border(width = 2.dp, color = Color(0xFF424242), shape = RoundedCornerShape(4.dp))
                .padding(20.dp),
        ) {
            // 選択肢操作ボタンエリア
            ControlButtons(lessonViewModel, songCompositionViewModel)

            Spacer(modifier = Modifier.height(20.dp))

            // 選択肢エリア
            BlockArea(lessonViewModel, songCompositionViewModel)
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}