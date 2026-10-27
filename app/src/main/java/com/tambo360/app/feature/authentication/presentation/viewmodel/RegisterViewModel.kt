package com.tambo360.app.feature.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.authentication.data.dto.RegisterUserRequest
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            val request = RegisterUserRequest(
                nombre = name,
                correo = email,
                contrasena = password
            )

            when (val result = repository.registerUser(request)) {
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
