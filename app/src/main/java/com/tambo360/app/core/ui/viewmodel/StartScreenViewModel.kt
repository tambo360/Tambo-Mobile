package com.tambo360.app.core.ui.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Login)
    val uiState = _uiState.asStateFlow()

    private val _tokenState = MutableStateFlow<String?>(null)
    val tokenState = _tokenState.asStateFlow()

    fun updateUiState(state: UiState) {
        _uiState.update {
            state
        }
    }

    fun handleDeepLink(intent: Intent?) {
        val uri = intent?.data ?: return

        if (uri.path == "/verificar") {
            val token = uri.getQueryParameter("token")
            if (!token.isNullOrEmpty()) {
                _tokenState.update { token }
                _uiState.update { UiState.Home }
            }
        }
    }

}

sealed class UiState {
    data object Login : UiState()
    data object Register : UiState()
    data object CheckEmail : UiState()
    data object Home : UiState()
}
