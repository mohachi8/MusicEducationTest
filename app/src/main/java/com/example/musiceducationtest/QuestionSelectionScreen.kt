package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Purple200

@Composable
fun QuestionSelectionScreen() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .padding(start = 100.dp, end = 100.dp, top = 80.dp)
    ) {
        items(5) { index ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp)
                    .aspectRatio(1f)
                    .background(Purple200)
                    .clickable { }
                    .border(
                        width = 8.dp,
                        color = Color.Black
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "レッスン1",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "きらきら星",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}