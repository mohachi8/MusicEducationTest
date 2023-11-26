package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.musiceducationtest.repository.LessonRepository
import com.example.musiceducationtest.model.LessonDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// レッスンデータの管理をするViewModel
class LessonManagerViewModel(application: Application) : AndroidViewModel(application) {

    /* -------------------------- 変数の定義 -------------------------- */
    private val lessonRepository = LessonRepository(application) // この部分はレポジトリの実装に依存します
    private val _selectedLesson = MutableStateFlow<LessonDataModel?>(null)

    val selectedLesson: StateFlow<LessonDataModel?> = _selectedLesson
    val allLessons = lessonRepository.getAllLessons()


    /* -------------------------- 処理 -------------------------- */
    // レッスンを選択
    fun selectLesson(lessonId: String) {
        _selectedLesson.value = lessonRepository.getLessonById(lessonId)
    }
}