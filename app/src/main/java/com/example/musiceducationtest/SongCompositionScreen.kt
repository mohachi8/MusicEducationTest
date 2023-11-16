package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Teal200

@Composable
fun SongCompositionScreen() {
    Column(modifier = Modifier.padding(20.dp)) {
        // フローチャート表示エリア
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 「曲を再生」ボタン
            FlowChartMusicStartButton()

            Spacer(modifier = Modifier.width(20.dp))

            // フローチャート
            FlowChart()
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 選択肢エリア
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .border(width = 4.dp, color = Color(0xFF424242))
                .padding(20.dp),
        ) {
            // 選択肢操作ボタンエリア
            ControlButtons()

            Spacer(modifier = Modifier.height(20.dp))

            // 選択肢エリア
            BlockArea()
        }
    }
}

@Composable
fun FlowChartMusicStartButton() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Teal200)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
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

@Composable
fun FlowChart() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background((Color(0xFFEEEEEE)))
            .border(width = 4.dp, color = Color(0xFF424242))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Box(
                modifier = Modifier
                    .size(100.dp)
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
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "次へ",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        items(10) { index ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "次へ",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        item {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray)
            )
        }
    }
}

@Composable
fun ControlButtons() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "ブロック エリア",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = { /*TODO*/ }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "えらぶ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "えらぶ"
                )
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = { /*TODO*/ }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "けす",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "けす"
                )
            }
        }
    }
}

@Composable
fun BlockArea() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(20) { index ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
                    .background(Color.LightGray)
                    .clickable { }
            )
        }
    }
}