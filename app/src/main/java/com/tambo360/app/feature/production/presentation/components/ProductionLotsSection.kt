package com.tambo360.app.feature.production.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R

@Composable
fun ProductionLotsSection(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Listado de lotes",
            color = Color(0xFF060901),
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = CircleShape)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD0D5DD),
                    shape = CircleShape
                )
                .padding(start = 48.dp, end = 16.dp, top = 14.dp, bottom = 14.dp)
        ) {
            Text(
                text = "Buscar lote...",
                color = Color(0xFF565B63),
                fontSize = 16.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Normal
            )
        }

        content()
    }
}

@Composable
fun ProductionLotCard(
    date: String,
    code: String,
    name: String,
    liters: String,
    shrinkage: String,
    isCompleted: Boolean,
    onOptionsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFE4E7EC),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 4.dp, top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Lote - $date",
                    color = Color(0xFF565B63),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "$code · $name",
                    color = Color(0xFF0F1216),
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(onClick = onOptionsClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_option),
                    contentDescription = "",
                    tint = Color(0xFF0F1216)
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFFE4E7EC)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF0F1216),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    ) {
                        append(liters)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF565B63),
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    ) {
                        append(" · Merma $shrinkage")
                    }
                }
            )

            LotStatusBadge(isCompleted = isCompleted)
        }
    }
}

@Composable
private fun LotStatusBadge(
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isCompleted) Color(0xFFDCF1BB) else Color(0xFFFFE7E8)
    val contentColor = if (isCompleted) Color(0xFF334C00) else Color(0xFFD01D21)
    val statusText = if (isCompleted) "Completo" else "Incompleto"

    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(color = contentColor, shape = CircleShape)
        )
        Text(
            text = statusText,
            color = contentColor,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
