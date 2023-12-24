package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Shapes
import com.example.musiceducationtest.viewmodel.LessonViewModel
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun BlockArea(
    lessonViewModel: LessonViewModel,
    songCompositionViewModel: SongCompositionViewModel
) {
    val lesson by lessonViewModel.selectedLesson.collectAsState()
    val selectedBlock by songCompositionViewModel.selectedBlock.collectAsState()
    val isPlaying by songCompositionViewModel.isPlaying.collectAsState()
    val flowChartBlocks by songCompositionViewModel.flowChartBlocks.collectAsState()

    // 選択されたレッスンの flowChartBlocks が null でないことを確認
    lesson?.flowChartBlocks?.let { blocks ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(blocks.size) { index ->
                val block = blocks[index]
                val isSelected = block == selectedBlock
                val isBlockAdded = flowChartBlocks.contains(block)
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .width(150.dp)
                        .aspectRatio(1f)
                        .clip(Shapes.small)
                        .clickable(
                            enabled = !isBlockAdded,
                            onClick = {
                                songCompositionViewModel.selectBlock(block)
                                block.musicResId?.let { musicResId ->
                                    songCompositionViewModel.playBlockMusic(musicResId)
                                }
                            }
                        )
                        // ブロックが選択されているときに枠を表示
                        .border(
                            width = if (isSelected && !isBlockAdded) 4.dp
                            else if (!isBlockAdded) 1.dp
                            else 0.1.dp,
                            color = if (isSelected && !isBlockAdded) Purple500
                            else Color(0xFF424242),
                            shape = Shapes.small
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = block.imageResId),
                        contentDescription = block.id,
                        modifier = Modifier.alpha(if (isBlockAdded) 0.2f else 1f)
                    )
                    // 選択されたブロックが再生中の時にアイコンを表示
                    if (isPlaying && isSelected) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "再生中",
                                    tint = Purple200,
                                    modifier = Modifier
                                        .size(30.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}