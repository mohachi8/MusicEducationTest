package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.musiceducationtest.repository.LessonRepository
import com.example.musiceducationtest.model.LessonDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// レッスンデータの管理をするViewModel
@HiltViewModel
class LessonManagerViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : ViewModel() {

    /* -------------------------- 変数の定義 -------------------------- */
    //private val lessonRepository = LessonRepository(application) // この部分はレポジトリの実装に依存します
    private val _selectedLesson = MutableStateFlow<LessonDataModel?>(null)

    val selectedLesson: StateFlow<LessonDataModel?> = _selectedLesson
    val allLessons = lessonRepository.getAllLessons()


    /* -------------------------- 処理 -------------------------- */
    // レッスンを選択
    fun selectLesson(lessonId: String) {
        _selectedLesson.value = lessonRepository.getLessonById(lessonId)
    }
}