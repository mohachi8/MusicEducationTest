package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun SongCompositionFlowChart(songCompositionViewModel: SongCompositionViewModel) {
    val flowChartBlocks by songCompositionViewModel.flowChartBlocks.collectAsState()

    // flowChartBlocksを使って各ブロックの画像を表示
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(flowChartBlocks) { index, block ->
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .width(110.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Purple200),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = block.imageResId),
                    contentDescription = "Block Image",
                )
            }
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "次へ",
                modifier = Modifier.size(40.dp)
            )
            if (index == flowChartBlocks.size - 1) {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .width(110.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Purple500.copy(alpha = 0.1f))
                        .border(
                            width = 2.dp,
                            color = Purple500,
                            shape = RoundedCornerShape(4.dp)
                        ),
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