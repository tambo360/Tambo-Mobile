package com.tambo360.app.feature.production.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductionTopBar(
    name: String,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Text(
                text = "Lotes de producción",
                color = Color(0xFF0F1216),
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.W700
            )
        },
        actions = {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = Color(0xFFDCF1BB),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    color = Color(0xFF334C00),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            }

            IconButton(onClick = onFilterClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "",
                    tint = Color(0xFF0F1216),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}
