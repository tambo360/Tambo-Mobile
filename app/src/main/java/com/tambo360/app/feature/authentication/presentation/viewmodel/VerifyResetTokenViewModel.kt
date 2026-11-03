package com.tambo360.app.feature.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.authentication.data.dto.VerifyResetTokenRequest
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import com.tambo360.app.feature.authentication.presentation.viewmodel.states.VerifyResetTokenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyResetTokenViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerifyResetTokenUiState())
    val uiState = _uiState.asStateFlow()

    fun verifyResetToken(token: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "", data = null) }

            val request = VerifyResetTokenRequest(
                token = token
            )

            when (val result = repository.verifyResetToken(request)) {
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
