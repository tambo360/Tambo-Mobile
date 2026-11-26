package com.tambo360.app.feature.home.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.core.util.capitalizeFirst
import com.tambo360.app.core.util.formatCompactCurrency
import com.tambo360.app.core.util.formatCurrency
import com.tambo360.app.core.util.formatNumber
import com.tambo360.app.feature.home.presentation.components.DashboardSection
import com.tambo360.app.feature.home.presentation.components.ProductionChartSection
import com.tambo360.app.feature.home.presentation.components.TodayProduction
import com.tambo360.app.feature.home.presentation.viewmodel.HomeUiState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit,
    uiState: HomeUiState = HomeUiState()
) {
    val scrollState = rememberScrollState()
    val summary = uiState.currentMonthSummary
    val litrosTotalesText = summary?.let { formatNumber(it.actualLeches) } ?: "--"
    val mermaText = summary?.let { "${formatNumber(it.actualMermas)} L" } ?: "--"
    val freeLiters = uiState.freeLiters
    val costosTotalesText = if (summary != null || freeLiters != null) {
        val totalCostos = (summary?.actualCostos ?: 0.0) + (freeLiters?.costosGenerales ?: 0.0)
        formatCompactCurrency(totalCostos)
    } else {
        "--"
    }
    val litrosLibresText = freeLiters?.let { "${formatNumber(it.litrosLibres)} L" } ?: "--"
    val precioMercadoText = uiState.marketPrice?.let { formatCurrency(it.precioLitro) } ?: "--"
    val produccionPoints = uiState.lechesCantidadChart?.resultado.orEmpty()
    val mermasPoints = uiState.lechesMermasChart?.resultado.orEmpty()

    val mesesChart = (produccionPoints.ifEmpty { mermasPoints })
        .map { it.mes.capitalizeFirst() }

    val produccionValues = List(mesesChart.size) { index ->
        produccionPoints.getOrNull(index)?.valor?.toFloat() ?: 0f
    }
    val mermasValues = List(mesesChart.size) { index ->
        mermasPoints.getOrNull(index)?.valor?.toFloat() ?: 0f
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(state = scrollState)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Tambo La Esperanza",
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 24.sp,
            color = Color(0xFF565B63),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "¡Bienvenido Ramón!",
            fontSize = 26.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 32.5.sp,
            color = Color(0xFF0F1216),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        DashboardSection(
            litrosTotales = litrosTotalesText,
            merma = mermaText,
            costosTotales = costosTotalesText,
            litrosLibres = litrosLibresText,
            precioMercadoHoy = precioMercadoText
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Comparación de costo",
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 28.sp,
            color = Color(0xFF0F1216),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProductionChartSection(
            meses = mesesChart,
            mermasActuales = mermasValues,
            costosActuales = produccionValues
        )
        Spacer(modifier = Modifier.height(20.dp))
        TodayProduction(
            lotes = uiState.lotes,
            onSeeAllClick = onSeeAllClick
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}
