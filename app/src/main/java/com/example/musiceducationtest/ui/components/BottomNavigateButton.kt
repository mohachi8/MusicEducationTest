package com.example.musiceducationtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.ui.theme.Teal200

// ボトムナビゲーションバーボタン
@Composable
fun BottomNavigateButton(
    imageVector: ImageVector,
    label: String,
    backgroundColor: Color,
    enabled: Boolean, // ボタンが有効かどうか（ボタンが無効だと灰色表示になり、押せなくなる）
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(height = 80.dp, width = 80.dp)
            .background(if (enabled) backgroundColor else Color.Gray)
            .clickable(enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = if (enabled) Color.White else Color.LightGray,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = label,
                color = if (enabled) Color.White else Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// プレビュー（もどるボタンの場合）
@Preview(showBackground = true)
@Composable
fun BottomNavigateButtonPreview() {
    BottomNavigateButton(
        imageVector = Icons.Default.KeyboardArrowLeft, // 例として標準のホームアイコンを使用
        label = "もどる",
        backgroundColor = Teal200,
        enabled = true,
        onClick = {}
    )
}