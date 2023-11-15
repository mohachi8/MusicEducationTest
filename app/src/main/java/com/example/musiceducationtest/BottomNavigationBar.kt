package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Purple500
import com.example.musiceducationtest.ui.theme.Teal200


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
