package com.example.musiceducationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    text: String
) {
    Row(
        modifier = Modifier
            .background(Color(0xFFEEEEEE))
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}