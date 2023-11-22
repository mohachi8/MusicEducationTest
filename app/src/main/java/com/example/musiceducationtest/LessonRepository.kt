package com.example.musiceducationtest

import android.app.Application

class LessonRepository(val application: Application) {
    private val lessons = listOf(
        Lesson(
            id = "1",
            title = "レッスン1",
            songTitle = "きらきら星",
            description = "このレッスンでは「きらきら星」を題材にした学習を行います。",
            musicFiles = "music1.mp3",
            answers = listOf("Answer1", "Answer2")
        ),
        Lesson(
            id = "2",
            title = "レッスン2",
            songTitle = "うみはひろいな",
            description = "このレッスンでは「うみはひろいな」を題材にした学習を行います。",
            musicFiles = "music2.mp3",
            answers = listOf("Answer3", "Answer4")
        )
        // 他のレッスンを追加...
    )

    fun getLessonById(id: String): Lesson? {
        return lessons.find { it.id == id }
    }

    fun getAllLessons(): List<Lesson> {
        return lessons
    }
}