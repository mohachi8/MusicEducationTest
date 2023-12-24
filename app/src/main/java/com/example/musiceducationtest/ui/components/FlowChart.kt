package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MusicNote
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
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Shapes
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun FlowChart(songCompositionViewModel: SongCompositionViewModel) {
    val flowChartBlocks by songCompositionViewModel.flowChartBlocks.collectAsState()
    val isPlaying by songCompositionViewModel.isPlaying.collectAsState()
    val selectedBlock by songCompositionViewModel.selectedBlock.collectAsState()
    val currentlyPlayingBlock by songCompositionViewModel.currentlyPlayingBlock.collectAsState()
    val previousBlock  by songCompositionViewModel.previousBlock.collectAsState()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(flowChartBlocks) { block ->
            val isSelected = block == selectedBlock // このブロックが現在選択されているかどうか
            val currentlyPlaying = block == currentlyPlayingBlock // このブロックが現在再生されているかどうか
            val previous = block == previousBlock // このブロックが繰り返し終わりの１つ前のブロックかどうか

            Box(
                modifier = Modifier
                    .size(
                        width = if (block.id == "repeatStart" || block.id == "repeatEnd") 80.dp else 160.dp,
                        height = 160.dp
                    )
                    .background(Purple200)
                    .clickable { // 押されたら音を再生
                        songCompositionViewModel.selectBlock(block)
                        block.musicResId?.let { musicResId ->
                            songCompositionViewModel.playBlockMusic(musicResId)
                        }
                    }
                , contentAlignment = Alignment.Center
            ) {
                // 選択肢の画像
                Image(
                    painter = painterResource(id = block.imageResId),
                    contentDescription = "Block Image",
                )

                // 小節の区切り線（小節の右側に表示）
                Spacer(
                    modifier = Modifier
                        .height(34.dp)
                        .width(1.dp) // ボーダーの太さ
                        .background(if (block.id == "repeatStart" || previous) Color.Transparent else Color.Black)
                        .align(Alignment.CenterEnd)
                )

                // ブロックが再生中の時に表示されるアイコン
                if ((isPlaying && isSelected) || currentlyPlaying) {
                    Box(modifier = Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ){
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier
                                .width(2.dp)
                                .height(10.dp)
                                .background(Purple200)
                            )
                            Box(modifier = Modifier
                                .weight(1f)
                                .height(2.dp)
                                .background(Purple200)
                            )
                            Icon(
                                imageVector = Icons.Default.MusicNote,
                                contentDescription = "再生中",
                                tint = Purple200,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Box(modifier = Modifier
                                .weight(1f)
                                .height(2.dp)
                                .background(Purple200)
                            )
                            Box(modifier = Modifier
                                .width(2.dp)
                                .height(10.dp)
                                .background(Purple200)
                            )
                        }
                    }
                }
            }
        }

        // 一番最後に空の小節と追加ボタンを表示
        item {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .aspectRatio(1f)
                , contentAlignment = Alignment.Center
            ) {
                // 空の小節
                Image(
                    painter = painterResource(id = R.drawable.empty_music_score),
                    contentDescription = "TrebleClef",
                    modifier = Modifier.height(160.dp)
                )

                // プラスボタン
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(Shapes.small)
                        .background(Purple500.copy(alpha = 0.1f))
                        .border(
                            width = 1.dp,
                            color = Purple500,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable { songCompositionViewModel.addToFlowChart() }, // クリックで選択したブロックをフローチャートに追加
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "追加",
                        tint = Purple500,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}