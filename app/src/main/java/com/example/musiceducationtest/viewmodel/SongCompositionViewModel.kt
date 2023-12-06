package com.example.musiceducationtest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongCompositionViewModel @Inject constructor(
) : ViewModel() {
    // 選択されたブロックのIDを保持する変数
    private val _selectedBlockId = MutableStateFlow<String?>(null)
    val selectedBlockId: StateFlow<String?> = _selectedBlockId

    // ブロックを選択するメソッド
    fun selectBlock(blockId: String) {
        _selectedBlockId.value = blockId
    }
}