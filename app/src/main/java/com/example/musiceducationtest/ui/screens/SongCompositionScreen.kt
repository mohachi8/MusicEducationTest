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
import androidx.navigation.compose.rememberNavController
import com.example.musiceducationtest.repository.LessonRepository
import com.example.musiceducationtest.ui.components.BlockArea
import com.example.musiceducationtest.ui.components.ControlButtons
import com.example.musiceducationtest.ui.components.FlowChartMusicStartButton
import com.example.musiceducationtest.ui.components.SongCompositionFlowChart
import com.example.musiceducationtest.viewmodel.LessonViewModel

@Composable
fun SongCompositionScreen(lessonViewModel: LessonViewModel) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {

        Spacer(modifier = Modifier.weight(1f))

        // フローチャート表示エリア
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 「曲を再生」ボタン
            FlowChartMusicStartButton()

            Spacer(modifier = Modifier.width(20.dp))

            // フローチャート
            SongCompositionFlowChart()
        }

        Spacer(modifier = Modifier.weight(1f))

        // 選択肢エリア
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .border(width = 4.dp, color = Color(0xFF424242))
                .padding(20.dp),
        ) {
            // 選択肢操作ボタンエリア
            ControlButtons()

            Spacer(modifier = Modifier.height(20.dp))

            // 選択肢エリア
            BlockArea()
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}


// プレビュー表示
@Preview(showBackground = true, device = "id:pixel_c")
@Composable
fun PreviewSongCompositionScreen() {
    // ダミーデータまたはモックビューモデルを作成
    val dummyLessonViewModel = createDummyLessonViewModel()

    // SongCompositionScreenのプレビュー
    SongCompositionScreen(lessonViewModel = dummyLessonViewModel)
}
fun createDummyLessonViewModel(): LessonViewModel {
    return LessonViewModel(lessonRepository = LessonRepository())
}