package com.tambo360.app.feature.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardSection(
    litrosTotales: String,
    merma: String,
    costosTotales: String,
    litrosLibres: String,
    precioMercadoHoy: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MetricCard(
                    title = "Litros totales",
                    value = "$litrosTotales L",
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Merma",
                    value = merma,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MetricCard(
                    title = "Costos totales",
                    value = costosTotales,
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Litros libres",
                    value = litrosLibres,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        MarketPriceCard(
            title = "Precio de\nmercado hoy",
            value = precioMercadoHoy,
            modifier = Modifier
                .weight(1.1f)
                .fillMaxHeight()
        )
    }
}

@Composable
private fun MetricCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(20.dp)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color.White,
            Color(0xFFEAF9F0)
        )
    )
    var valueFontSize by remember(value) { mutableStateOf(16.sp) }

    Column(
        modifier = modifier
            .clip(cardShape)
            .background(brush = backgroundGradient)
            .border(
                width = 1.dp,
                color = Color(0xFFE5E7EB),
                shape = cardShape
            )
            .padding(horizontal = 6.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFF565B63),
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.sp,
            maxLines = 1
        )
        Text(
            text = value,
            color = Color(0xFF0F1216),
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 32.sp,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { result ->
                if (result.hasVisualOverflow && valueFontSize > 10.sp) {
                    valueFontSize = (valueFontSize.value - 1f).coerceAtLeast(10f).sp
                }
            }
        )
    }
}

@Composable
private fun MarketPriceCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(20.dp)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color.White,
            Color(0xFFEAF9F0)
        )
    )

    Column(
        modifier = modifier
            .clip(cardShape)
            .background(brush = backgroundGradient)
            .border(
                width = 1.dp,
                color = Color(0xFFE5E7EB),
                shape = cardShape
            )
            .padding(horizontal = 12.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = Color(0xFF4B5563),
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = value,
            color = Color(0xFF0F1216),
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp,
            maxLines = 1
        )
    }
}
