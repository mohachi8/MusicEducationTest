package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.viewmodel.LessonViewModel

@Composable
fun BlockArea(lessonViewModel: LessonViewModel) {
    val lesson by lessonViewModel.selectedLesson.collectAsState()
/*
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(20) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .width(110.dp)
                    .aspectRatio(1f)
                    .background(Color.LightGray)
                    .clickable { }
            )
        }
    }
}

 */

    // 選択されたレッスンの flowChartBlocks が null でないことを確認
    lesson?.flowChartBlocks?.let { blocks ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(blocks.size) { index ->
                // 各ブロックのデータを取得
                val block = blocks[index]
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .width(110.dp)
                        .aspectRatio(1f)
                        .background(Color.LightGray)
                        .clickable { /* ブロックをクリックした時の処理 */ }
                ) {
                    // ここで BlockDataModel の imageResId を使用して画像を表示
                    Image(
                        painter = painterResource(id = block.imageResId),
                        contentDescription = null // 適切な説明を設定
                    )
                }
            }
        }
    }
}