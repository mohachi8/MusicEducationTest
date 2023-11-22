package com.example.musiceducationtest

data class Lesson(
    val id: String, //lesson id
    val title: String, //
    val songTitle: String, //
    val description: String,
    val musicFiles: String,
    val answers: List<String>
)