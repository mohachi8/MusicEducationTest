package com.example.musiceducationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.MusicEducationTestTheme
import com.example.musiceducationtest.ui.theme.Purple200
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicEducationTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopBar() },
                        bottomBar = { BottomNavigationBar() }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            MainContent()
                        }
                    }
                }
            }
        }
    }
}

// トップバー
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .background(Color(0xFFEEEEEE))
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "ブロックを ならびかえて もとの曲を さいげん しましょう。",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

// ボトムナビゲーションバー
@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier.background(Color(0xFFEEEEEE)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavigateButton(
            imageVector = Icons.Default.Clear,
            label = "やめる",
            backgroundColor = Color(0xFF424242)
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomMusicPlayer()
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigateButton(
            imageVector = Icons.Default.KeyboardArrowLeft,
            label = "もどる",
            backgroundColor = Teal200
        )
        BottomNavigateButton(
            imageVector = Icons.Default.KeyboardArrowRight,
            label = "つぎへ",
            backgroundColor = Purple500
        )
    }
}

//  音楽プレイヤー
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

// ボトムナビゲーションバーボタン
@Composable
fun BottomNavigateButton(
    imageVector: ImageVector,
    label: String,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(backgroundColor)
            .clickable { /* クリックされた時の処理*/ },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = label,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MainContent() {
    Column {
        // フローチャート表示エリア
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 曲の再生ボタン
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

            Spacer(modifier = Modifier.width(20.dp))

            // フローチャート
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background((Color(0xFFEEEEEE)))
                    .border(width = 4.dp, color = Color.Black)
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

        // 選択肢エリア
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background((Color(0xFFEEEEEE)))
                .border(width = 4.dp, color = Color.Black)
                .padding(20.dp),
        ) {
            // 選択肢操作ボタンエリア
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
            Spacer(modifier = Modifier.height(20.dp))

            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 100.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .background(Color.White)
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                items(60) { index ->
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
    }
}