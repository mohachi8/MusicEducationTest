package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.repository.LessonRepository
import com.example.musiceducationtest.ui.components.BlockArea
import com.example.musiceducationtest.ui.components.ControlButtons
import com.example.musiceducationtest.ui.components.FlowChartMusicStartButton
import com.example.musiceducationtest.ui.components.SongCompositionFlowChart
import com.example.musiceducationtest.viewmodel.LessonViewModel

@Composable
fun SongCompositionScreen(lessonViewModel: LessonViewModel) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {

        Spacer(modifier = Modifier.weight(1f))

        // フローチャート表示エリア
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .border(width = 2.dp, color = Color(0xFF424242))
                .padding(10.dp)
        ) {

            // 「曲を再生」ボタン
            FlowChartMusicStartButton()

            Spacer(modifier = Modifier.width(10.dp))

            // 縦区切り線
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .width(1.dp)
                    .background(Color(0xFF424242))
            )
            Spacer(modifier = Modifier.width(10.dp))

            // フローチャート
            SongCompositionFlowChart()
        }

        Spacer(modifier = Modifier.weight(1f))

        // 選択肢エリア
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .border(width = 2.dp, color = Color(0xFF424242))
                .padding(20.dp),
        ) {
            // 選択肢操作ボタンエリア
            ControlButtons()

            Spacer(modifier = Modifier.height(20.dp))

            // 選択肢エリア
            BlockArea(lessonViewModel)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}


// プレビュー表示
@Preview(showBackground = true, device = "id:pixel_c")
@Composable
fun PreviewSongCompositionScreen() {
    val dummyLessonViewModel = createDummyLessonViewModel()
    SongCompositionScreen(lessonViewModel = dummyLessonViewModel)
}

fun createDummyLessonViewModel(): LessonViewModel {
    return LessonViewModel(lessonRepository = LessonRepository())
}