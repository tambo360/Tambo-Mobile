package com.tambo360.app.feature.authentication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.data.TokenRepository
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.authentication.data.dto.LoginUserRequest
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import com.tambo360.app.feature.authentication.presentation.viewmodel.states.LoginUiState
import com.tambo360.app.feature.onboarding.domain.repository.OrganizationStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val orgStorage: OrganizationStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            val request = LoginUserRequest(
                correo = email,
                contrasena = password
            )

            when (val result = repository.loginUser(request)) {
                is NetworkResult.Success -> {
                    val user = result.data.data.user
                    val orgActual = user.organizaciones.firstOrNull()
                    val estActual = orgActual?.establecimientos?.firstOrNull()
                    if (estActual?.cuestionarioCompletado == true){
                        orgStorage.setOnboardingCompleted(true)
                        orgStorage.saveIds(
                            idEst = estActual.idEstablecimiento,
                            idOrg = orgActual.idOrganizacion
                        )
                        orgStorage.saveName(name = orgActual.nombre)
                        orgStorage.saveNameUser(nameUser = user.nombre)
                    }
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
