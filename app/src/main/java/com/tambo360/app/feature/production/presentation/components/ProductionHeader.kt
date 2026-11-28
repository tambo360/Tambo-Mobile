package com.tambo360.app.feature.production.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R

@Composable
fun ProductionHeader(
    tamboName: String,
    date: String,
    location: String,
    temperature: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "$tamboName · $date",
            color = Color(0xFF565B63),
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Normal
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoPill(
                iconRes = R.drawable.ic_location,
                text = location,
                fontWeight = FontWeight.Medium
            )

            InfoPill(
                iconRes = R.drawable.ic_temperature,
                text = temperature,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun InfoPill(
    iconRes: Int,
    text: String,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = Color.White, shape = CircleShape)
            .border(
                width = 1.dp,
                color = Color(0xFFD0D5DD),
                shape = CircleShape
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color(0xFF527602),
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            color = Color(0xFF0F1216),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = fontWeight
        )
    }
}
