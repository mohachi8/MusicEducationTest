package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun BlockArea(
    lessonViewModel: LessonViewModel,
    songCompositionViewModel: SongCompositionViewModel
) {
    val lesson by lessonViewModel.selectedLesson.collectAsState()
    val selectedBlockId by songCompositionViewModel.selectedBlockId.collectAsState()

    // 選択されたレッスンの flowChartBlocks が null でないことを確認
    lesson?.flowChartBlocks?.let { blocks ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(blocks.size) { index ->
                val block = blocks[index]
                val isSelected = block.id == selectedBlockId
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .width(110.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            songCompositionViewModel.selectBlock(block.id)
                            songCompositionViewModel.playBlockMusic(block.musicResId)
                        }
                        .border(
                            width = if (isSelected) 4.dp else 0.dp,
                            color = if (isSelected) Color.Red else Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = block.imageResId),
                        contentDescription = block.id
                    )
                }
            }
        }
    }
}