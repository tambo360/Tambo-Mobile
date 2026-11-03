package com.tambo360.app.feature.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.authentication.data.dto.ResetPasswordRequest
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import com.tambo360.app.feature.authentication.presentation.viewmodel.states.ResetPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResetPasswordUiState())
    val uiState = _uiState.asStateFlow()

    fun resetPassword(token: String, newPassword: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "", data = null) }

            val request = ResetPasswordRequest(
                token = token,
                nuevaContrasena = newPassword
            )

            when (val result = repository.resetPassword(request)) {
                is NetworkResult.Success -> {
                    _uiState.update { it.copy(loading = false, data = result.data) }
                }

                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(loading = false, error = cleanMessage) }
                }
            }
        }
    }
}
