package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Purple500
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
            columns = GridCells.Adaptive(120.dp),
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
                        .width(110.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp))
                        .clickable(
                            enabled = !isBlockAdded,
                            onClick = {
                                songCompositionViewModel.selectBlock(block)
                                songCompositionViewModel.playBlockMusic(block.musicResId)
                            }
                        )
                        // ブロックが選択されているときに枠を表示
                        .border(
                            width = if (isSelected && !isBlockAdded) 4.dp else 0.dp,
                            color = if (isSelected && !isBlockAdded) Purple500 else Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = block.imageResId),
                        contentDescription = block.id,
                        modifier = Modifier.alpha(if (isBlockAdded) 0.2f else 1f)
                    )
                    // 選択されたブロックが再生中の時にアイコンを表示
                    if (isPlaying && isSelected) {
                        Icon(
                            imageVector = Icons.Default.PlayCircle,
                            contentDescription = "再生中",
                            tint = Purple200,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(80.dp)
                                .alpha(0.8f)
                        )
                    }
                }
            }
        }
    }
}