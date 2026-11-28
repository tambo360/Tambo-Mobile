package com.tambo360.app.feature.production.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun LotsPagination(
    currentPage: Int,
    totalPages: Int,
    totalLotes: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lotesLabel = if (totalLotes == 1) "lote" else "lotes"

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Página $currentPage de $totalPages · $totalLotes $lotesLabel",
            color = Color(0xFF565B63),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(onClick = onPreviousClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "Página anterior",
                    tint = Color(0xFF0F1216),
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(onClick = onNextClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_next),
                    contentDescription = "Página siguiente",
                    tint = Color(0xFF0F1216),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
