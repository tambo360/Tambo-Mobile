package com.tambo360.app.feature.home.presentation.viewmodel

import com.tambo360.app.feature.home.domain.model.CurrentMonthSummary
import com.tambo360.app.feature.home.domain.model.DashboardChart
import com.tambo360.app.feature.home.domain.model.FreeLiters
import com.tambo360.app.feature.home.domain.model.GeneralCosts
import com.tambo360.app.feature.home.domain.model.LoteItem
import com.tambo360.app.feature.home.domain.model.MarketPrice

data class HomeUiState(
    val isLoading: Boolean = true,
    val currentMonthSummary: CurrentMonthSummary? = null,
    val generalCosts: GeneralCosts? = null,
    val freeLiters: FreeLiters? = null,
    val marketPrice: MarketPrice? = null,
    val lechesCantidadChart: DashboardChart? = null,
    val lechesMermasChart: DashboardChart? = null,
    val lotes: List<LoteItem> = emptyList(),
    val organizationName: String = "",
    val userName: String = ""
)
