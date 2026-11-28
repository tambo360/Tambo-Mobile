package com.tambo360.app.feature.production.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.feature.production.presentation.components.LotsPagination
import com.tambo360.app.feature.production.presentation.components.ProductionHeader
import com.tambo360.app.feature.production.presentation.components.ProductionLotCard
import com.tambo360.app.feature.production.presentation.components.ProductionLotsSection

@Composable
fun ProductionContent(
    modifier: Modifier = Modifier,
    onRegisterLotClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(state = scrollState)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ProductionHeader(
            tamboName = "Tambo La Esperanza",
            date = "Febrero 2026",
            location = "Curuzú Cuatiá, Corrientes",
            temperature = "27°"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onRegisterLotClick,
            modifier = Modifier
                .height(50.dp)
                .align(Alignment.End),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF527602),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp
            ),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                text = "+ Registrar lote",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        ProductionLotsSection {
            ProductionLotCard(
                date = "16/02/2026",
                code = "001",
                name = "Rodeo Alto",
                liters = "5.000 L",
                shrinkage = "80 L",
                isCompleted = false,
                onOptionsClick = {}
            )
            ProductionLotCard(
                date = "18/02/2026",
                code = "002",
                name = "Rodeo Bajo",
                liters = "3.200 L",
                shrinkage = "25 L",
                isCompleted = true,
                onOptionsClick = {}
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        LotsPagination(
            currentPage = 1,
            totalPages = 1,
            totalLotes = 2,
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
