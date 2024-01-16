package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musiceducationtest.R
import com.example.musiceducationtest.viewmodel.LessonViewModel

@Composable
fun ExplanationScreen2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "つぎに すすみましょう．いつでも このがめんに もどってこれます．", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painterResource(id = R.drawable.sample),
            contentDescription = "",
            modifier = Modifier
                .weight(0.3f)
                .border(
                    1.dp,
                    Color.Black
                )
        )
    }
}