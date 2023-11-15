package com.example.musiceducationtest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.theme.Purple500

@Composable
fun BottomMusicPlayer() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.5f)
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "再生",
            tint = Purple500,
            modifier = Modifier
                .size(60.dp)
                .clickable { }
        )
        Slider(
            value = 0.8F, onValueChange = {})
    }
}