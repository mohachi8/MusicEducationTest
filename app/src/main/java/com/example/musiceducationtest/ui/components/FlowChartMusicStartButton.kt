package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Teal200
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun FlowChartMusicStartButton(songCompositionViewModel: SongCompositionViewModel) {
    val isPlaying = songCompositionViewModel.isPlayingFlowChart.collectAsState().value

    Button(
        onClick = { songCompositionViewModel.startFlowChartMusic() },
        colors = ButtonDefaults.buttonColors(Teal200)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = "再生",
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "曲を再生",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}