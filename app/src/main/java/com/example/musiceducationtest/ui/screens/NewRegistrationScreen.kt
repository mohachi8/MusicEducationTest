package com.example.musiceducationtest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun NewRegistrationScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // ユーザー名入力フィールド
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("ユーザー名", fontSize = 20.sp) },
            modifier = Modifier
                .width(250.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    //  keyboardController?.hide() // キーボードを閉じる
                }
            ),
        )


        Spacer(modifier = Modifier.height(20.dp))

        // パスワード入力フィールド
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("パスワード", fontSize = 20.sp) },
            modifier = Modifier
                .width(250.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    //keyboardController?.hide() // キーボードを閉じる
                }
            ),
        )

        // パスワードを再入力
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("パスワードを再入力", fontSize = 20.sp) },
            modifier = Modifier
                .width(250.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    //keyboardController?.hide() // キーボードを閉じる
                }
            ),
        )

        Spacer(modifier = Modifier.height(60.dp))

        // 「登録」ボタン
        Button(
            onClick = {
                navController.navigate("lessonSelectionScreen")
            },
        ) {
            Text(
                text = "登録",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp)
            )
        }
    }
}