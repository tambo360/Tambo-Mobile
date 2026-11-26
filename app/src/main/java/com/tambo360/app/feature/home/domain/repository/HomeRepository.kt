package com.tambo360.app.feature.home.domain.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.feature.home.data.mapper.DashboardChartType
import com.tambo360.app.feature.home.domain.model.CurrentMonthSummary
import com.tambo360.app.feature.home.domain.model.DashboardChart
import com.tambo360.app.feature.home.domain.model.FreeLiters
import com.tambo360.app.feature.home.domain.model.GeneralCosts
import com.tambo360.app.feature.home.domain.model.LoteItem
import com.tambo360.app.feature.home.domain.model.MarketPrice
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun syncCurrentMonthSummary(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<CurrentMonthSummary>

    fun getCurrentMonthSummary(): Flow<CurrentMonthSummary?>
    suspend fun syncGeneralCosts(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<GeneralCosts>

    fun getGeneralCosts(): Flow<GeneralCosts?>
    suspend fun syncFreeLiters(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<FreeLiters>

    fun getFreeLiters(): Flow<FreeLiters?>
    suspend fun syncMarketPrice(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<MarketPrice>

    fun getMarketPrice(): Flow<MarketPrice?>
    suspend fun syncDashboardChart(
        idOrganizacion: String,
        idEstablecimiento: String,
        tipo: DashboardChartType
    ): NetworkResult<DashboardChart>

    fun getDashboardChart(tipo: DashboardChartType): Flow<DashboardChart?>
    suspend fun syncLotes(
        idOrganizacion: String,
        idEstablecimiento: String,
        page: Int? = null,
        limit: Int? = null,
        orden: String? = null,
        estado: Boolean? = null,
        nombre: String? = null,
        producto: String? = null,
        numeroLote: Int? = null,
        fechaDesde: String? = null,
        fechaHasta: String? = null
    ): NetworkResult<List<LoteItem>>

    fun getLotes(): Flow<List<LoteItem>>
}
