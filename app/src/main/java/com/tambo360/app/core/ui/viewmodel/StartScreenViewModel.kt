package com.tambo360.app.core.ui.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import com.tambo360.app.feature.onboarding.domain.repository.OrganizationStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val orgStorage: OrganizationStorage
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
        val isOnboardingCompleted = orgStorage.isOnboardingCompleted()

        val initialState = when {
            !isLoggedIn -> UiState.Login
            !isOnboardingCompleted -> UiState.Onboarding
            else -> UiState.Home
        }

        _uiState.update { initialState }
    }

    fun updateUiState(state: UiState) {
        _uiState.update {
            state
        }
    }

    fun handleDeepLink(intent: Intent?) {
        val uri = intent?.data ?: return
        val token = uri.getQueryParameter("token")

        when (uri.path) {
            "/verificar" -> {
                if (!token.isNullOrEmpty()) {
                    _tokenState.update { token }
                    _uiState.update { UiState.CheckEmail() }
                }
            }

            "/recuperar-contrasena" -> {
                if (!token.isNullOrEmpty()) {
                    _tokenState.update { token }
                    _uiState.update { UiState.ForgotCheck() }
                }
            }
        }
    }

    fun onLoginSuccess() {
        val nextState = if (orgStorage.isOnboardingCompleted()) {
            UiState.Home
        } else {
            UiState.Onboarding
        }
        _uiState.update { nextState }
    }

}

sealed class UiState {
    data object Login : UiState()
    data object Register : UiState()
    data object Forgot : UiState()
    data object ChangePassword : UiState()
    data class ForgotCheck(val email: String = "") : UiState()
    data class CheckEmail(val email: String = "") : UiState()
    data object Onboarding : UiState()
    data object Home : UiState()
}
