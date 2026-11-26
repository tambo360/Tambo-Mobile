package com.tambo360.app.feature.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductionChartSection(
    meses: List<String>,
    mermasActuales: List<Float>,
    costosActuales: List<Float>,
    modifier: Modifier = Modifier,
    title: String = "Leche | Producción"
) {
    val maxValue = maxOf(
        mermasActuales.maxOrNull() ?: 0f,
        costosActuales.maxOrNull() ?: 0f
    ).coerceAtLeast(1f)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFE5E7EB)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                color = Color(0xFF565B63),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                meses.indices.forEach { index ->
                    val merma = mermasActuales.getOrElse(index) { 0f }.coerceAtLeast(0f)
                    val costo = costosActuales.getOrElse(index) { 0f }.coerceAtLeast(0f)

                    val mermaFraction = (merma / maxValue).coerceIn(0f, 1f)
                    val costoFraction = (costo / maxValue).coerceIn(0f, 1f)

                    val orderedBars = listOf(
                        mermaFraction to Color(0xFF84A894),
                        costoFraction to Color(0xFF6BC4B3)
                    ).sortedByDescending { it.first }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        orderedBars.forEach { (fraction, color) ->
                            if (fraction > 0f) {
                                Box(
                                    modifier = Modifier
                                        .width(40.dp)
                                        .fillMaxHeight(fraction)
                                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                                        .background(color)
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFE5E7EB)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                meses.forEach { mes ->
                    Text(
                        text = mes.take(3).replaceFirstChar { it.uppercase() },
                        color = Color(0xFF565B63),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChartLegendItem(
                    color = Color(0xFF84A894),
                    label = "Merma actual"
                )
                ChartLegendItem(
                    color = Color(0xFF6BC4B3),
                    label = "Costo actual"
                )
            }
        }
    }
}

@Composable
private fun ChartLegendItem(
    color: Color,
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = label,
            color = Color(0xFF565B63),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp
        )
    }
}
