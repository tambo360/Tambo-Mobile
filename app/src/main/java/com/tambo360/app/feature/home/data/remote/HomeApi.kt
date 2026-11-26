package com.tambo360.app.feature.home.data.remote

import com.tambo360.app.feature.home.data.dto.response.CurrentMonthSummaryResponse
import com.tambo360.app.feature.home.data.dto.response.DashboardChartResponse
import com.tambo360.app.feature.home.data.dto.response.FreeLitersResponse
import com.tambo360.app.feature.home.data.dto.response.GeneralCostsResponse
import com.tambo360.app.feature.home.data.dto.response.LotesResponse
import com.tambo360.app.feature.home.data.dto.response.MarketPriceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApi {

    @GET("dashboard/mes-actual")
    suspend fun getCurrentMonthSummary(
        @Header("x-organizacion-id") idOrganizacion: String,
        @Header("x-establecimiento-id") idEstablecimiento: String
    ): Response<CurrentMonthSummaryResponse>

    @GET("dashboard/costos-generales")
    suspend fun getGeneralCosts(
        @Header("x-organizacion-id") idOrganizacion: String,
        @Header("x-establecimiento-id") idEstablecimiento: String
    ): Response<GeneralCostsResponse>

    @GET("dashboard/litros-libres")
    suspend fun getFreeLiters(
        @Header("x-organizacion-id") idOrganizacion: String,
        @Header("x-establecimiento-id") idEstablecimiento: String
    ): Response<FreeLitersResponse>

    @GET("dashboard/precio-mercado")
    suspend fun getMarketPrice(
        @Header("x-organizacion-id") idOrganizacion: String,
        @Header("x-establecimiento-id") idEstablecimiento: String
    ): Response<MarketPriceResponse>

    @GET("dashboard/grafico")
    suspend fun getDashboardChart(
        @Header("x-organizacion-id") idOrganizacion: String,
        @Header("x-establecimiento-id") idEstablecimiento: String,
        @Query("producto") producto: String,
        @Query("metrica") metrica: String
    ): Response<DashboardChartResponse>

    @GET("lote/listar")
    suspend fun getLotes(
        @Header("x-organizacion-id") idOrganizacion: String,
        @Header("x-establecimiento-id") idEstablecimiento: String,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("orden") orden: String? = null,
        @Query("estado") estado: Boolean? = null,
        @Query("nombre") nombre: String? = null,
        @Query("producto") producto: String? = null,
        @Query("numeroLote") numeroLote: Int? = null,
        @Query("fecha_desde") fechaDesde: String? = null,
        @Query("fecha_hasta") fechaHasta: String? = null
    ): Response<LotesResponse>

}
