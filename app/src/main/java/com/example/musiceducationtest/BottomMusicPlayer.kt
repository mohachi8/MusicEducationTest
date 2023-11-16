package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.theme.Purple500

@Composable
fun BottomMusicPlayer(viewModel: MusicPlayerViewModel) {
    val isPlaying = viewModel.isPlaying.collectAsState().value
    val playbackPosition = viewModel.playbackPosition.collectAsState().value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.5f)
    ) {
        Box(
            modifier = Modifier
                .background(color = Purple500)
                .clickable { viewModel.togglePlayPause() }
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Slider(
            value = playbackPosition,
            onValueChange = viewModel::onSliderValueChanged
        )
    }
}