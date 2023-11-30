package com.example.musiceducationtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class BottomBarViewModel : ViewModel() {
    // ダイアログ表示の状態を管理
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    fun toggleDialog(show: Boolean) {
        _showDialog.value = show
    }

    fun navigateHome(navController: NavController) {
        navController.navigate("lessonSelection")
        _showDialog.value = false
    }
}