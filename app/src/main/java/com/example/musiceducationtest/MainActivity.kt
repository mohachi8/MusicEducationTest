package com.example.musiceducationtest

import android.graphics.drawable.Icon
import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.MusicEducationTestTheme

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
                    Column {
                        // トップバー
                        Row(
                            modifier = Modifier
                                .background(Color(0xFFEEEEEE))
                                .padding(20.dp)
                                .fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "ブロックを並び替えてもとの曲を再現しましょう。",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        }

                        // フローチャート表示エリア
                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // 曲の再生ボタン
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(Color(0xFFFF7043))
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
                                            .background(Color.Blue)
                                    )
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

                        // ボトムナビゲーションバー
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .background(Color(0xFFEEEEEE)),
                            ) {
                                Row {
                                    BottomNavigateButton(
                                        imageVector = Icons.Default.Clear,
                                        label = "やめる",
                                        backgroundColor = Color(0xFF424242)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    BottomNavigateButton(
                                        imageVector = Icons.Default.KeyboardArrowLeft,
                                        label = "もどる",
                                        backgroundColor = Color(0xFF26A69A)
                                    )
                                    BottomNavigateButton(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        label = "つぎへ",
                                        backgroundColor = Color(0xFFFF7043)
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

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