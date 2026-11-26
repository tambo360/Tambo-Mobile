package com.tambo360.app.feature.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R

@Composable
fun TodayProduction(
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Producción de hoy",
            color = Color(0xFF0F1216),
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 28.sp
        )

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onSeeAllClick)
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "Ver todo",
                color = Color(0xFF527602),
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 24.sp
            )
            Icon(
                painter = painterResource(R.drawable.arrowright),
                contentDescription = "",
                tint = Color(0xFF527602),
                modifier = Modifier.size(20.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 0.8.dp,
            color = Color(0xFFDDE2E8)
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TodayProductionItem(
                id = "005",
                title = "Queso pategrás",
                subtitle = "384 Kg · Merma 20 Kg",
                price = "$100.000"
            )
            TodayProductionItem(
                id = "004",
                title = "Leche entera",
                subtitle = "1.200 L · Merma 20 L",
                price = "$204.000"
            )
            TodayProductionItem(
                id = "005",
                title = "Queso pategrás",
                subtitle = "384 Kg · Merma 20 Kg",
                price = "$100.000"
            )
            TodayProductionItem(
                id = "004",
                title = "Leche entera",
                subtitle = "1.200 L · Merma 20 L",
                price = "$204.000"
            )
        }
    }
}

@Composable
fun TodayProductionItem(
    id: String,
    title: String,
    subtitle: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 64.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEDF1F4)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = id,
                    color = Color(0xFF0F1216),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    maxLines = 1
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = title,
                    color = Color(0xFF0F1216),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle,
                    color = Color(0xFF565B63),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = price,
                color = Color(0xFF0F1216),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 24.sp,
                letterSpacing = 0.sp,
                maxLines = 1
            )
        }
        HorizontalDivider(
            thickness = 0.8.dp,
            color = Color(0xFFDDE2E8)
        )
    }
}
