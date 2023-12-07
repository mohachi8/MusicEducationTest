package com.example.musiceducationtest.viewmodel

import com.example.musiceducationtest.helper.SoundPoolHelper
import androidx.lifecycle.ViewModel
import com.example.musiceducationtest.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongCompositionViewModel @Inject constructor(
    private val lessonRepository: LessonRepository,
    private val soundPoolHelper: SoundPoolHelper
) : ViewModel() {
    // 選択されたブロックのIDを保持する変数
    private val _selectedBlockId = MutableStateFlow<String?>(null)
    val selectedBlockId: StateFlow<String?> = _selectedBlockId

    fun playSelectedBlockSound(blockId: String) {
        val lesson = lessonRepository.getLessonById(blockId)
        val soundId = lesson?.flowChartBlocks?.find { it.id == blockId }?.musicResId
        soundId?.let { soundPoolHelper.playSound(it) }
    }

    // ブロックを選択するメソッド
    fun selectBlock(blockId: String) {
        _selectedBlockId.value = blockId
    }
}