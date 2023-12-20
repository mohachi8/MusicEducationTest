package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
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
import com.example.musiceducationtest.R
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Shapes
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowChart(songCompositionViewModel: SongCompositionViewModel) {
    val flowChartBlocks by songCompositionViewModel.flowChartBlocks.collectAsState()
    val isPlaying by songCompositionViewModel.isPlaying.collectAsState()
    val selectedBlock by songCompositionViewModel.selectedBlock.collectAsState()
    val currentlyPlayingBlock by songCompositionViewModel.currentlyPlayingBlock.collectAsState()

    /*
        FlowRow(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
        ) {
    /*
            Box(modifier = Modifier.height(120.dp), contentAlignment = Alignment.Center) {
                FlowChartMusicStartButton(songCompositionViewModel)
            }

     */

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

            flowChartBlocks.forEachIndexed { index, block ->
                val isSelected = block == selectedBlock
                val currentlyPlaying = block == currentlyPlayingBlock
                Box(
                    modifier = Modifier
                        //.padding(5.dp)
                        .width(110.dp)
                        .aspectRatio(1f)
                        .clip(Shapes.small)
                        .background(Purple200)
                        .clickable { // 押されたら音を再生
                            songCompositionViewModel.selectBlock(block)
                            block.musicResId?.let { musicResId ->
                                songCompositionViewModel.playBlockMusic(musicResId)
                            }
                        }
                        .border(
                            width = 1.dp,
                            color = Color(0xFF424242),
                            shape = Shapes.small
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = block.imageResId),
                        contentDescription = "Block Image",
                    )
                    // 選択されたブロックが再生中の時にアイコンを表示
                    if ((isPlaying && isSelected) || currentlyPlaying) {
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
                /*
                Box(modifier = Modifier.height(120.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "次へ",
                        modifier = Modifier.size(40.dp)
                    )
                }

                 */
                // 一番最後にプラスのBoxを表示
                if (index == flowChartBlocks.size - 1) {
                    Box(
                        modifier = Modifier
                            //.padding(5.dp)
                            .width(110.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Purple500.copy(alpha = 0.1f))
                            .border(
                                width = 2.dp,
                                color = Purple500.copy(alpha = 0.5f),
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


     */

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(flowChartBlocks) { block ->
            val isSelected = block == selectedBlock
            val currentlyPlaying = block == currentlyPlayingBlock
            Box(
                modifier = Modifier
                    //.padding(5.dp)
                    .width(160.dp)
                    .aspectRatio(1f)
                    //.clip(RoundedCornerShape(4.dp))
                    .background(Purple200)
                    .clickable { // 押されたら音を再生
                        songCompositionViewModel.selectBlock(block)
                        block.musicResId?.let { musicResId ->
                            songCompositionViewModel.playBlockMusic(musicResId)
                        }
                    }
//                    .border(
//                        width = 0.01.dp,
//                        color = Color(0xFF424242),
//                        //shape = Shapes.small
//                    ),
                ,contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = block.imageResId),
                    contentDescription = "Block Image",
                )

//                // 右側にボーダーを追加
//                Spacer(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .width(1.dp) // ボーダーの太さ
//                        .background(Color.Black) // ボーダーの色
//                        .align(Alignment.CenterEnd)
//                )

                // 選択されたブロックが再生中の時にアイコンを表示
                if ((isPlaying && isSelected) || currentlyPlaying) {
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
            /*
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "次へ",
                modifier = Modifier.size(40.dp)
            )

             */
        }
        // 一番最後にプラスのBoxを表示
        item {
            Box(
                modifier = Modifier
//                    .padding(5.dp)
                    .width(160.dp)
                    .aspectRatio(1f)
//                    .clip(RoundedCornerShape(4.dp))
//                    .background(Purple500.copy(alpha = 0.1f))
//                    .border(
//                        width = 2.dp,
//                        color = Purple500.copy(alpha = 0.5f),
//                        shape = RoundedCornerShape(4.dp)
//                    )
                ,contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.empty_music_score),
                    contentDescription = "TrebleClef",
                    modifier = Modifier.height(160.dp)
                )
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(Shapes.small)
                        .background(Purple500.copy(alpha = 0.1f))
                        .border(
                            width = 1.dp,
                            color = Purple500,//.copy(alpha = 0.5f),
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