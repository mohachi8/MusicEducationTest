package com.example.musiceducationtest.ui.components

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Teal200
import com.example.musiceducationtest.viewmodel.MusicPlayerViewModel

// 音楽プレイヤーのGUI
@Composable
fun BottomMusicPlayer(viewModel: MusicPlayerViewModel) {
    val isPlaying = viewModel.isPlaying.collectAsState().value
    val playbackPosition = viewModel.playbackPosition.collectAsState().value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .requiredWidthIn(min = 300.dp)
            .fillMaxWidth(0.5f)
            .height(80.dp)
            //.background(Color.LightGray)
            .padding(start = 10.dp, end = 20.dp)
    ) {
        // 再生、一時停止ボタン
        IconButton(onClick = { viewModel.togglePlayPause() }) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Purple500,
                modifier = Modifier
                    .size(60.dp)
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

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
fun BottomMusicPlayerPreview() {
    val dummyViewModel = MusicPlayerViewModel(application = Application())
    BottomMusicPlayer(viewModel = dummyViewModel)
}