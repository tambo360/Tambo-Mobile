package com.tambo360.app.core.ui.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Login)
    val uiState = _uiState.asStateFlow()

    private val _tokenState = MutableStateFlow<String?>(null)
    val tokenState = _tokenState.asStateFlow()


    init {
        checkInitialState()
    }

    private fun checkInitialState() {
        val isLoggedIn = repository.isLoggedIn()
        if (isLoggedIn) {
            _uiState.update { UiState.Home }
        } else {
            _uiState.update { UiState.Login }
        }
    }

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
                _uiState.update { UiState.CheckEmail() }
            }
        }
    }

}

sealed class UiState {
    data object Login : UiState()
    data object Register : UiState()
    data class CheckEmail(val email: String = "") : UiState()
    data object Home : UiState()
}
