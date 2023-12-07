package com.example.musiceducationtest.viewmodel

import SoundPoolHelper
import androidx.lifecycle.ViewModel
import com.example.musiceducationtest.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongCompositionViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : ViewModel() {
    // 選択されたブロックのIDを保持する変数
    private val _selectedBlockId = MutableStateFlow<String?>(null)
    val selectedBlockId: StateFlow<String?> = _selectedBlockId

    fun playSelectedBlockSound(blockId: String, soundPoolHelper: SoundPoolHelper) {
        val soundId = lessonRepository.getLessonById(blockId)?.musicResId
        soundId?.let { soundPoolHelper.playSound(it) }
    }

    // ブロックを選択するメソッド
    fun selectBlock(blockId: String) {
        _selectedBlockId.value = blockId
    }
}