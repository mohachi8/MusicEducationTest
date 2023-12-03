package com.example.musiceducationtest.ui.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BlockArea() {
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