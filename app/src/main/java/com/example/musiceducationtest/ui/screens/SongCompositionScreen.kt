package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.musiceducationtest.ui.theme.Shapes
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun SongCompositionScreen(
    lessonViewModel: LessonViewModel,
    songCompositionViewModel: SongCompositionViewModel
) {
    val selectedBlock by songCompositionViewModel.selectedBlock.collectAsState()

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
            Box(
                modifier = Modifier
                    .size(80.dp, 160.dp)
                //.width(160.dp)
                //.weight(0.2f)
                //.aspectRatio(1f)
                //.clip(Shapes.small)
                //.padding(10.dp)
                //.border(width = 2.dp, color = Color(0xFF424242), shape = Shapes.small)
                //.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star_treble_clef),
                    contentDescription = "TrebleClef",
                    modifier = Modifier.height(160.dp)
                )
            }

            // フローチャート
            FlowChart(songCompositionViewModel)
        }

        Spacer(modifier = Modifier.weight(1f))

        // 選択肢エリア
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .clip(Shapes.small)
                .border(width = 2.dp, color = Color(0xFF424242), shape = Shapes.small)
                .padding(20.dp),
        ) {
            ControlButtons(lessonViewModel, songCompositionViewModel)

            Column(
                modifier = Modifier
                    .weight(0.8f)
//                    .background((Color(0xFFEEEEEE)))
//                    .clip(Shapes.small)
//                    .border(width = 2.dp, color = Color(0xFF424242), shape = Shapes.small)
//                    .padding(10.dp),
            ) {
                // 選択肢エリア
                BlockArea(lessonViewModel, songCompositionViewModel)
            }

            //ControlButtons(lessonViewModel, songCompositionViewModel)

            /*
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .aspectRatio(1f)
                    .clip(Shapes.small)
                    .padding(10.dp)
                    .border(width = 2.dp, color = Color(0xFF424242), shape = Shapes.small)
                    .padding(10.dp)
            ) {
                val selectedBlockSnapshot = selectedBlock
                if (selectedBlockSnapshot != null) {
                    Image(
                        painter = painterResource(id = selectedBlockSnapshot.imageResId),
                        contentDescription = "Block Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .border(width = 3.dp, color = Color(0xFF424242), shape = Shapes.small)
                    )
                }
            }

             */
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}