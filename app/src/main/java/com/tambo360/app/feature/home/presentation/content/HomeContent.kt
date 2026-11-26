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
import com.tambo360.app.feature.home.presentation.components.DashboardSection
import com.tambo360.app.feature.home.presentation.components.ProductionChartSection
import com.tambo360.app.feature.home.presentation.components.TodayProduction

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit
) {
    val scrollState = rememberScrollState()
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
            litrosTotales = "48.200",
            merma = "1.240 L",
            costosTotales = "$3,2M",
            litrosLibres = "$27,5",
            precioMercadoHoy = "$385.50"
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
            meses = listOf("Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"),
            mermasActuales = listOf(9600f, 2200f, 2000f, 3200f, 2100f),
            costosActuales = listOf(1900f, 9000f, 11000f, 10200f, 12400f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TodayProduction(
            onSeeAllClick = onSeeAllClick
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}
