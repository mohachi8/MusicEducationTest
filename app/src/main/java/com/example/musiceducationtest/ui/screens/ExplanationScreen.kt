package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.R
import com.example.musiceducationtest.viewmodel.LessonViewModel

@Composable
fun ExplanationScreen(lessonViewModel: LessonViewModel) {
    val lesson by lessonViewModel.selectedLesson.collectAsState()

    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(0.7f)
        ) {
            // それぞれのレッスンに対応した説明を表示
            Text(text = lesson?.description ?: "説明なし", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "これから しょうせつをくみたてて おてほんのきょくを さいげん します．", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "したの おんがくプレイヤーで おてほんの きょくを ききましょう.", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "きけたら みぎしたの「すすむ」ボタンをおして つぎに すすみましょう.", fontSize = 20.sp)
        }
        Column(modifier = Modifier.weight(0.3f)) {
            Image(
                painterResource(id = R.drawable.explain01),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .border(
                        1.dp,
                        Color.Black
                    )
            )
            Image(
                painterResource(id = R.drawable.explain02),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .border(
                        1.dp,
                        Color.Black
                    )
            )
            Image(
                painterResource(id = R.drawable.explain03),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .border(
                        1.dp,
                        Color.Black
                    )
            )
        }
    }
}