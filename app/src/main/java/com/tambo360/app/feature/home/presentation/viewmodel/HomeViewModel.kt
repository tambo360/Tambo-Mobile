package com.tambo360.app.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.feature.home.data.mapper.DashboardChartType
import com.tambo360.app.feature.home.domain.model.CurrentMonthSummary
import com.tambo360.app.feature.home.domain.model.DashboardChart
import com.tambo360.app.feature.home.domain.model.FreeLiters
import com.tambo360.app.feature.home.domain.model.GeneralCosts
import com.tambo360.app.feature.home.domain.model.LoteItem
import com.tambo360.app.feature.home.domain.model.MarketPrice
import com.tambo360.app.feature.home.domain.repository.HomeRepository
import com.tambo360.app.feature.onboarding.domain.repository.OrganizationStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRepository: HomeRepository,
    organizationStorage: OrganizationStorage
) : ViewModel() {

    private val metricsFlow = combine(
        homeRepository.getCurrentMonthSummary(),
        homeRepository.getGeneralCosts(),
        homeRepository.getFreeLiters(),
        homeRepository.getMarketPrice()
    ) { currentMonth, generalCosts, freeLiters, marketPrice ->
        HomeMetrics(
            currentMonthSummary = currentMonth,
            generalCosts = generalCosts,
            freeLiters = freeLiters,
            marketPrice = marketPrice
        )
    }

    private val chartsAndLotesFlow = combine(
        homeRepository.getDashboardChart(DashboardChartType.LECHES_CANTIDAD),
        homeRepository.getDashboardChart(DashboardChartType.LECHES_MERMAS),
        homeRepository.getLotes()
    ) { cantidadChart, mermasChart, lotes ->
        HomeChartsAndLotes(
            lechesCantidadChart = cantidadChart,
            lechesMermasChart = mermasChart,
            lotes = lotes
        )
    }

    val uiState: StateFlow<HomeUiState> = combine(
        metricsFlow,
        chartsAndLotesFlow
    ) { metrics, chartsAndLotes ->
        HomeUiState(
            isLoading = false,
            organizationName = organizationStorage.getName().orEmpty(),
            userName = organizationStorage.getNameUser().orEmpty(),
            currentMonthSummary = metrics.currentMonthSummary,
            generalCosts = metrics.generalCosts,
            freeLiters = metrics.freeLiters,
            marketPrice = metrics.marketPrice,
            lechesCantidadChart = chartsAndLotes.lechesCantidadChart,
            lechesMermasChart = chartsAndLotes.lechesMermasChart,
            lotes = chartsAndLotes.lotes
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState(
            isLoading = true,
            organizationName = organizationStorage.getName().orEmpty(),
            userName = organizationStorage.getNameUser().orEmpty()
        )
    )

    private data class HomeMetrics(
        val currentMonthSummary: CurrentMonthSummary?,
        val generalCosts: GeneralCosts?,
        val freeLiters: FreeLiters?,
        val marketPrice: MarketPrice?
    )

    private data class HomeChartsAndLotes(
        val lechesCantidadChart: DashboardChart?,
        val lechesMermasChart: DashboardChart?,
        val lotes: List<LoteItem>
    )
}
