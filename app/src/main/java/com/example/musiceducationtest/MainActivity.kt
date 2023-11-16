package com.example.musiceducationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
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
                    Scaffold(
                        topBar = { TopBar(text = "ブロックを ならびかえて もとの曲を さいげん しましょう。") },
                        bottomBar = { BottomNavigationBar() }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            SongCompositionScreen()
                        }
                    }
                }
            }
        }
    }
}