package com.example.musiceducationtest.ui.components

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel

// 音楽プレイヤーのGUI
@Composable
fun MusicPlayer(viewModel: MusicPlayerViewModel) {
    val isPlaying = viewModel.isPlaying.collectAsState().value
    val playbackPosition = viewModel.playbackPosition.collectAsState().value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .requiredWidthIn(min = 300.dp)
            .fillMaxWidth(0.4f)
            .height(70.dp)
            .padding(start = 10.dp, end = 20.dp)
    ) {
        // 再生、一時停止ボタン
        IconButton(onClick = { viewModel.togglePlayPause() }) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Purple500,
                modifier = Modifier
                    .size(50.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

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

@Preview(showBackground = true)
@Composable
fun MusicPlayerPreview() {
    val dummyViewModel = MusicPlayerViewModel(application = Application())
    MusicPlayer(viewModel = dummyViewModel)
}