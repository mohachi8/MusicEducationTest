package com.example.musiceducationtest

import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

                        Spacer(modifier = Modifier.height(20.dp))

                        // フローチャート表示エリア
                        Row(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            // 曲の再生ボタン
                            Button(onClick = { /*TODO*/ }) {
                                Row {
                                    Text(text = "曲を再生")
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "再生"
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                            // フローチャート
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background((Color(0xFFEEEEEE)))
                            ) {
                                Card() {
                                    
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
