package com.tambo360.app.feature.onboarding.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductionStep(
    altoAnimalCount: String,
    onAltoAnimalCountChange: (String) -> Unit,
    altoRationCost: String,
    onAltoRationCostChange: (String) -> Unit,
    onAddAltoBreed: (String, String) -> Unit,
    bajoAnimalCount: String,
    onBajoAnimalCountChange: (String) -> Unit,
    bajoRationCost: String,
    onBajoRationCostChange: (String) -> Unit,
    onAddBajoBreed: (String, String) -> Unit,
    secoAnimalCount: String,
    onSecoAnimalCountChange: (String) -> Unit,
    secoRationCost: String,
    onSecoRationCostChange: (String) -> Unit,
    onAddSecoBreed: (String, String) -> Unit
) {
    var expandedCategory by remember { mutableStateOf("Rodeo Alto") }
    val mockBreedList = listOf(
        "HOLANDO_ARGENTINO", "JERSEY", "PARDO_SUIZO", "GIR_LECHERO", "HOLANDO_JERSEY_CRUZA",
        "AYRSHIRE","NORMANDO","BROWN_SWISS","MONTBELIARDE","SIMMENTAL_LECHERO","OTRAS")
    var altoSelectedBreed by remember { mutableStateOf<String?>(null) }
    var altoBreedCount by remember { mutableStateOf("") }

    var bajoSelectedBreed by remember { mutableStateOf<String?>(null) }
    var bajoBreedCount by remember { mutableStateOf("") }

    var secoSelectedBreed by remember { mutableStateOf<String?>(null) }
    var secoBreedCount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "¿Cuántos animales tenés por categoría?",
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 30.sp,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(4.dp))
        RodeoCategoryCard(
            title = "Rodeo Alto",
            subtitle = if (altoAnimalCount.isEmpty()) "Sin datos" else "$altoAnimalCount animales",
            animalCount = altoAnimalCount,
            onAnimalCountChange = onAltoAnimalCountChange,
            rationCost = altoRationCost,
            onRationCostChange = onAltoRationCostChange,
            breedOptions = mockBreedList,
            selectedBreed = altoSelectedBreed,
            onBreedSelected = { altoSelectedBreed = it },
            breedCount = altoBreedCount,
            onBreedCountChange = { altoBreedCount = it },
            onAddBreedClick = { breed, count ->
                onAddAltoBreed(breed, count)
                altoSelectedBreed = null
                altoBreedCount = ""
            },
            isExpanded = expandedCategory == "Rodeo Alto",
            onExpandClick = {
                expandedCategory = if (expandedCategory == "Rodeo Alto") "" else "Rodeo Alto"
            }
        )

        RodeoCategoryCard(
            title = "Rodeo Bajo",
            subtitle = if (bajoAnimalCount.isEmpty()) "Sin datos" else "$bajoAnimalCount animales",
            animalCount = bajoAnimalCount,
            onAnimalCountChange = onBajoAnimalCountChange,
            rationCost = bajoRationCost,
            onRationCostChange = onBajoRationCostChange,
            breedOptions = mockBreedList,
            selectedBreed = bajoSelectedBreed,
            onBreedSelected = { bajoSelectedBreed = it },
            breedCount = bajoBreedCount,
            onBreedCountChange = { bajoBreedCount = it },
            onAddBreedClick = { breed, count ->
                onAddBajoBreed(breed, count)
                bajoSelectedBreed = null
                bajoBreedCount = ""
            },
            isExpanded = expandedCategory == "Rodeo Bajo",
            onExpandClick = {
                expandedCategory = if (expandedCategory == "Rodeo Bajo") "" else "Rodeo Bajo"
            }
        )

        RodeoCategoryCard(
            title = "Rodeo en Seco",
            subtitle = if (secoAnimalCount.isEmpty()) "Sin datos" else "$secoAnimalCount animales",
            animalCount = secoAnimalCount,
            onAnimalCountChange = onSecoAnimalCountChange,
            rationCost = secoRationCost,
            onRationCostChange = onSecoRationCostChange,
            breedOptions = mockBreedList,
            selectedBreed = secoSelectedBreed,
            onBreedSelected = { secoSelectedBreed = it },
            breedCount = secoBreedCount,
            onBreedCountChange = { secoBreedCount = it },
            onAddBreedClick = { breed, count ->
                onAddSecoBreed(breed, count)
                secoSelectedBreed = null
                secoBreedCount = ""
            },
            isExpanded = expandedCategory == "Rodeo en Seco",
            onExpandClick = {
                expandedCategory = if (expandedCategory == "Rodeo en Seco") "" else "Rodeo en Seco"
            }
        )
    }
}
