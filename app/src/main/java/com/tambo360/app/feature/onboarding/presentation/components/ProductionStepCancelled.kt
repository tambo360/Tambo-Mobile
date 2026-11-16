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
fun ProductionStepCancelled(
    unicoAnimalCount: String,
    onUnicoAnimalCountChange: (String) -> Unit,
    unicoRationCost: String,
    onUnicoRationCostChange: (String) -> Unit,
    onAddUnicoBreed: (String, String) -> Unit,
    secaAnimalCount: String,
    onSecaAnimalCountChange: (String) -> Unit,
    secaRationCost: String,
    onSecaRationCostChange: (String) -> Unit,
    onAddSecaBreed: (String, String) -> Unit
) {
    var expandedCategory by remember { mutableStateOf("Rodeo Unico") }
    val mockBreedList = listOf(
        "HOLANDO_ARGENTINO", "JERSEY", "PARDO_SUIZO", "GIR_LECHERO", "HOLANDO_JERSEY_CRUZA",
        "AYRSHIRE","NORMANDO","BROWN_SWISS","MONTBELIARDE","SIMMENTAL_LECHERO","OTRAS")
    var unicoSelectedBreed by remember { mutableStateOf<String?>(null) }
    var unicoBreedCount by remember { mutableStateOf("") }

    var secaSelectedBreed by remember { mutableStateOf<String?>(null) }
    var secaBreedCount by remember { mutableStateOf("") }

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
            title = "Rodeo Unico",
            subtitle = if (unicoAnimalCount.isEmpty()) "Sin datos" else "$unicoAnimalCount animales",
            animalCount = unicoAnimalCount,
            onAnimalCountChange = onUnicoAnimalCountChange,
            rationCost = unicoRationCost,
            onRationCostChange = onUnicoRationCostChange,
            breedOptions = mockBreedList,
            selectedBreed = unicoSelectedBreed,
            onBreedSelected = { unicoSelectedBreed = it },
            breedCount = unicoBreedCount,
            onBreedCountChange = { unicoBreedCount = it },
            onAddBreedClick = { breed, count ->
                onAddUnicoBreed(breed, count)
                unicoSelectedBreed = null
                unicoBreedCount = ""
            },
            isExpanded = expandedCategory == "Rodeo Unico",
            onExpandClick = {
                expandedCategory = if (expandedCategory == "Rodeo Unico") "" else "Rodeo Unico"
            }
        )

        RodeoCategoryCard(
            title = "Rodeo en seca",
            subtitle = if (secaAnimalCount.isEmpty()) "Sin datos" else "$secaAnimalCount animales",
            animalCount = secaAnimalCount,
            onAnimalCountChange = onSecaAnimalCountChange,
            rationCost = secaRationCost,
            onRationCostChange = onSecaRationCostChange,
            breedOptions = mockBreedList,
            selectedBreed = secaSelectedBreed,
            onBreedSelected = { secaSelectedBreed = it },
            breedCount = secaBreedCount,
            onBreedCountChange = { secaBreedCount = it },
            onAddBreedClick = { breed, count ->
                onAddSecaBreed(breed, count)
                secaSelectedBreed = null
                secaBreedCount = ""
            },
            isExpanded = expandedCategory == "Rodeo en seca",
            onExpandClick = {
                expandedCategory = if (expandedCategory == "Rodeo en seca") "" else "Rodeo en seca"
            }
        )
    }
}
