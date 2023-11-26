package com.example.musiceducationtest.repository

import android.app.Application
import com.example.musiceducationtest.model.LessonDataModel

class LessonRepository(val application: Application) {
    private val lessons = listOf(
        LessonDataModel(
            id = "1",
            title = "レッスン1",
            songTitle = "きらきら星",
            description = "このレッスンでは「きらきら星」を題材にした学習を行います。",
            musicFiles = "music1.mp3",
            answers = listOf("Answer1", "Answer2")
        ),
        LessonDataModel(
            id = "2",
            title = "レッスン2",
            songTitle = "うみはひろいな",
            description = "このレッスンでは「うみはひろいな」を題材にした学習を行います。",
            musicFiles = "music2.mp3",
            answers = listOf("Answer3", "Answer4")
        )
        // 他のレッスンを追加...
    )

    fun getLessonById(id: String): LessonDataModel? {
        return lessons.find { it.id == id }
    }

    fun getAllLessons(): List<LessonDataModel> {
        return lessons
    }
}