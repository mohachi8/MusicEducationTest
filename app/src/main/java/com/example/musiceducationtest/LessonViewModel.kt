package com.example.musiceducationtest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LessonViewModel(application: Application) : AndroidViewModel(application) {
    private val lessonRepository = LessonRepository(application) // この部分はレポジトリの実装に依存します
    private val _selectedLesson = MutableStateFlow<Lesson?>(null)
    val selectedLesson: StateFlow<Lesson?> = _selectedLesson

    val allLessons = lessonRepository.getAllLessons()

    fun selectLesson(lessonId: String) {
        _selectedLesson.value = lessonRepository.getLessonById(lessonId)
    }
}