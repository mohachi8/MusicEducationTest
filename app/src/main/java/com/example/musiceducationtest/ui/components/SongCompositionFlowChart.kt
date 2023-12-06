package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Purple200

@Preview
@Composable
fun SongCompositionFlowChart() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .width(110.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Purple200),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "スタート",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "次へ",
                modifier = Modifier.size(40.dp)
            )
        }
        items(10) { index ->
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .width(110.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
            )
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "次へ",
                modifier = Modifier.size(40.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .width(110.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
            )
        }
    }
}