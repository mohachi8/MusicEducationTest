package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.R
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Shapes
import com.example.musiceducationtest.ui.theme.Teal200
import com.example.musiceducationtest.viewmodel.SongCompositionViewModel

@Composable
fun FlowChartMusicStartButton(songCompositionViewModel: SongCompositionViewModel) {
    val isPlaying = songCompositionViewModel.isPlayingFlowChart.collectAsState().value

    Box(
        modifier = Modifier
//            .padding(5.dp)
//            .size(110.dp, 110.dp)
//            .clip(Shapes.small)
            .clickable { songCompositionViewModel.startFlowChartMusic() }
//            .background(Purple500)
//            .border(
//                width = 4.dp,
//                color = Purple200,
//                shape = Shapes.small
//            ),
        ,contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.StopCircle else Icons.Default.PlayCircle,
                contentDescription = "再生",
                modifier = Modifier.size(40.dp),
                tint = Purple500
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = if (isPlaying) "曲を停止" else "曲を再生",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        /*
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.StopCircle else Icons.Default.PlayCircle,
                    contentDescription = "再生",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (isPlaying) "曲を停止" else "曲を再生",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.treble_clef),
                contentDescription = "Block Image",
                modifier = Modifier.height(110.dp)
            )
        }

         */
    }

    /*
    Button(
        onClick = { songCompositionViewModel.startFlowChartMusic() },
        colors = ButtonDefaults.buttonColors(Teal200),
        modifier = Modifier.size(110.dp, 110.dp)
    ) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.StopCircle else Icons.Default.PlayCircle,
                    contentDescription = "再生",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (isPlaying) "曲を停止" else "曲を再生",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.treble_clef),
                contentDescription = "Block Image",
                modifier = Modifier.fillMaxSize()
            )
        }
    }

     */
}