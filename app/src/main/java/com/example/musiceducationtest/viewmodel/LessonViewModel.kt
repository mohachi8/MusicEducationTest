package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.musiceducationtest.repository.LessonRepository
import com.example.musiceducationtest.model.LessonDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.example.musiceducationtest.helper.SoundPoolHelper


// レッスンデータの管理をするViewModel
@HiltViewModel
class LessonViewModel @Inject constructor(
    application: Application,
    private val lessonRepository: LessonRepository
) : ViewModel() {
    private val _selectedLesson = MutableStateFlow<LessonDataModel?>(null)

    val selectedLesson: StateFlow<LessonDataModel?> = _selectedLesson // 選択されたレッスンを保持する変数
    val allLessons = lessonRepository.getAllLessons() // 全種類のレッスンを保持する変数（問題選択画面で使用）

    // レッスンを選択した時に呼び出されるメソッド
    fun selectLesson(lessonId: String) {
        _selectedLesson.value = lessonRepository.getLessonById(lessonId)
    }


    // LessonViewModel内

    private val soundPoolHelper = SoundPoolHelper(application)

    fun loadLessonSounds(lessonId: String) {
        val lesson = lessonRepository.getLessonById(lessonId)
        lesson?.flowChartBlocks?.forEach { block ->
            soundPoolHelper.loadSound(block.musicResId)
        }
    }
}