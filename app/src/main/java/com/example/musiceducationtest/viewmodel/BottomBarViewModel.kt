package com.example.musiceducationtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject constructor() : ViewModel() {
    private val _showDialog = MutableStateFlow(false)

    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow() // ダイアログ表示の状態を管理

    // やめるボタンを押した時に呼び出されるメソッド
    fun toggleDialog(show: Boolean) {
        _showDialog.value = show
    }
}