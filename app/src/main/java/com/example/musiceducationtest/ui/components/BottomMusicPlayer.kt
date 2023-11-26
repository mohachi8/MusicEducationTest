package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel

// 音楽プレイヤーのGUI
@Composable
fun BottomMusicPlayer(viewModel: MusicPlayerViewModel) {

    // collectAsStateでviewModelの状態を反映
    val isPlaying = viewModel.isPlaying.collectAsState().value
    val playbackPosition = viewModel.playbackPosition.collectAsState().value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.5f)
    ) {

        // 再生、一時停止ボタン
        Box(
            modifier = Modifier
                .background(color = Purple500, shape = RoundedCornerShape(10))
                .clickable { viewModel.togglePlayPause() }
                .clip(RoundedCornerShape(50)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier
                    .size(60.dp)
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        // スライダー
        Slider(
            value = playbackPosition,
            // スライダーの値が変化した時に呼び出される
            onValueChange = viewModel::onSliderValueChanged,
            // スライダーの操作が完了した時に呼び出される
            onValueChangeFinished = viewModel::onSliderValueChangeFinished
        )
    }
}