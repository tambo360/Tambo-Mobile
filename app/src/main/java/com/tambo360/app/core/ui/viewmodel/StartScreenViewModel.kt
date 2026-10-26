package com.tambo360.app.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Register)
    val uiState = _uiState.asStateFlow()

    fun updateUiState(state: UiState) {
        _uiState.update {
            state
        }
    }

}

sealed class UiState {
    data object Register : UiState()
    data object Home : UiState()
}
