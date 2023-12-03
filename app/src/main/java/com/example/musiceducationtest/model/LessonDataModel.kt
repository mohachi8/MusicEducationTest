package com.example.musiceducationtest.model

data class LessonDataModel(
    val id: String, // レッスンのid
    val title: String, // レッスンタイトル
    val songTitle: String, // 曲の題名
    val description: String, // レッスンの説明
    val musicResId: Int, // 曲のリソースID（Int）
    val answers: List<String> // 問題の答え
)