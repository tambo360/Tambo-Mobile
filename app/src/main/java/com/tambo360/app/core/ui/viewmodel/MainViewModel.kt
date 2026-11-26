package com.tambo360.app.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.network.NetworkMonitor
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.home.data.mapper.DashboardChartType
import com.tambo360.app.feature.home.domain.repository.HomeRepository
import com.tambo360.app.feature.onboarding.domain.repository.OrganizationStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val orgStorage: OrganizationStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    @Volatile
    private var isSyncRunning = false

    init {
        observeNetworkChanges()
    }

    private fun observeNetworkChanges() {
        viewModelScope.launch {
            NetworkMonitor.isOnline.collect { connected ->
                _uiState.update { it.copy(isOnline = connected) }
                if (connected) syncAllData()
            }
        }
    }

    fun syncAllData() {
        val idOrg = orgStorage.getIdOrg().toString()
        val idEst = orgStorage.getIdEst().toString()
        if (isSyncRunning || !_uiState.value.isOnline) return
        isSyncRunning = true

        viewModelScope.launch {
            _uiState.update { it.copy(isSyncing = true, syncError = "") }

            try {
                syncHomeSection(
                    idOrganizacion = idOrg,
                    idEstablecimiento = idEst
                )
            } finally {
                isSyncRunning = false
                _uiState.update { it.copy(isSyncing = false) }
            }
        }
    }

    private suspend fun syncHomeSection(
        idOrganizacion: String,
        idEstablecimiento: String
    ) = supervisorScope {
        launch {
            when (
                val result = homeRepository.syncCurrentMonthSummary(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            ) {
                is NetworkResult.Success -> {}
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(syncError = cleanMessage) }
                }
            }
        }
        launch {
            when (
                val result = homeRepository.syncGeneralCosts(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            ) {
                is NetworkResult.Success -> {}
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(syncError = cleanMessage) }
                }
            }
        }
        launch {
            when (
                val result = homeRepository.syncFreeLiters(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            ) {
                is NetworkResult.Success -> {}
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(syncError = cleanMessage) }
                }
            }
        }
        launch {
            when (
                val result = homeRepository.syncMarketPrice(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            ) {
                is NetworkResult.Success -> {}
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(syncError = cleanMessage) }
                }
            }
        }
        launch {
            when (
                val result = homeRepository.syncDashboardChart(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento,
                    tipo = DashboardChartType.LECHES_CANTIDAD
                )
            ) {
                is NetworkResult.Success -> {}
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(syncError = cleanMessage) }
                }
            }
        }
        launch {
            when (
                val result = homeRepository.syncDashboardChart(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento,
                    tipo = DashboardChartType.LECHES_MERMAS
                )
            ) {
                is NetworkResult.Success -> {}
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(syncError = cleanMessage) }
                }
            }
        }
        launch {
            when (
                val result = homeRepository.syncLotes(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            ) {
                is NetworkResult.Success -> {}
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(syncError = cleanMessage) }
                }
            }
        }
    }
}

data class MainUiState(
    val isOnline: Boolean = true,
    val isSyncing: Boolean = false,
    val syncError: String = ""
)
