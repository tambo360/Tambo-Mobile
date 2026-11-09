package com.tambo360.app.feature.onboarding.presentation.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.feature.onboarding.presentation.components.CustomDropdown
import com.tambo360.app.feature.onboarding.presentation.components.CustomRadioButton
import com.tambo360.app.feature.onboarding.presentation.components.RodeoCategoryCard
import com.tambo360.app.feature.onboarding.presentation.components.StepsProgress

enum class ConfigStep(val stepNumber: Int) {
    LOCATION(1),
    ORDENE(2),
    PRODUCTION(3)
}

enum class ProductionStatus {
    NORMAL,
    ACCEPTED,
    CANCELED
}

@Composable
fun StepByStepContent() {
    var currentStep by remember { mutableStateOf(ConfigStep.LOCATION) }
    val totalSteps = 3
    val scrollState = rememberScrollState()
    var productionStatus by remember { mutableStateOf(ProductionStatus.NORMAL) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF9FAFC))
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        StepsProgress(
            currentStep = currentStep.stepNumber,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            when (currentStep) {
                ConfigStep.LOCATION -> LocationStep()
                ConfigStep.ORDENE -> {
                    OrdeneStep(
                        productionStatus = productionStatus,
                        onStatusChanged = { productionStatus = it }
                    )
                }

                ConfigStep.PRODUCTION -> {
                    when (productionStatus) {
                        ProductionStatus.ACCEPTED -> {}
                        ProductionStatus.CANCELED -> {}
                        ProductionStatus.NORMAL -> ProductionStep()
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    currentStep = when (currentStep) {
                        ConfigStep.PRODUCTION -> ConfigStep.ORDENE
                        ConfigStep.ORDENE -> ConfigStep.LOCATION
                        ConfigStep.LOCATION -> ConfigStep.LOCATION
                    }
                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                enabled = currentStep != ConfigStep.LOCATION,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text(
                    text = "Anterior",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = if (currentStep == ConfigStep.LOCATION) Color.Gray else Color(0xFF0F1216)
                )
            }
            Button(
                onClick = {
                    when (currentStep) {
                        ConfigStep.LOCATION -> currentStep = ConfigStep.ORDENE
                        ConfigStep.ORDENE -> currentStep = ConfigStep.PRODUCTION
                        ConfigStep.PRODUCTION -> {
                            // Lógica para finalizar el flujo e ir al Home
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF236E44),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text(
                    text = if (currentStep == ConfigStep.PRODUCTION) "Finalizar" else "Siguiente",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun LocationStep() {
    val provinciasMock = listOf("Buenos Aires", "Córdoba", "Santa Fe", "Entre Ríos", "La Pampa")
    val localidadesMock = listOf("Localidad A", "Localidad B", "Localidad C", "Localidad D")
    var selectedProvince by remember { mutableStateOf<String?>(null) }
    var selectedLocation by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Configurá tu Perfil",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.5.sp,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ayudanos a personalizar la experiencia de Tambo360 con los datos actuales de tu establecimiento",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
            color = Color(0xFF555B63)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "¿Dónde está tu tambo?",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 28.sp,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(24.dp))
        CustomDropdown(
            label = "Provincia",
            placeholder = "Seleccioná",
            items = provinciasMock,
            selectedItem = selectedProvince,
            onItemSelected = { selectedProvince = it },
            enabled = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomDropdown(
            label = "Localidad",
            subLabel = "Elegí primero la provincia.",
            placeholder = "Seleccioná",
            items = localidadesMock,
            selectedItem = selectedLocation,
            onItemSelected = { selectedLocation = it },
            enabled = selectedProvince != null
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun OrdeneStep(
    productionStatus: ProductionStatus,
    onStatusChanged: (ProductionStatus) -> Unit
) {
    var ordeñosPorDia by remember { mutableStateOf<Int?>(null) }
    var tipoInstalacion by remember { mutableStateOf<String?>(null) }
    var litrosPromedio by remember { mutableStateOf("") }
    var destinoProducto by remember { mutableStateOf<String?>(null) }
    var delPromedio by remember { mutableStateOf("") }
    var precioVenta by remember { mutableStateOf("") }
    val instalacionesMock = listOf("Fosa", "Brete", "Tandem", "Calesita")
    val destinosMock = listOf("Industria", "Cooperativa", "MasaZZ", "Venta Directa")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "¿Cuántas veces ordeñás por día?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Cantidad de ordeñes diarios.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF555B63)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(49.6.dp)
                .border(
                    width = 0.8.dp,
                    color = Color(0xFFBFC5CA),
                    shape = RoundedCornerShape(percent = 50)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..4) {
                val isSelected = ordeñosPorDia == i
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { ordeñosPorDia = i }
                        .background(
                            color = if (isSelected) Color(0xFF236E44).copy(0.7f) else Color.Transparent,
                            shape = when (i) {
                                1 -> RoundedCornerShape(
                                    topStartPercent = 50,
                                    bottomStartPercent = 50
                                )

                                4 -> RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50)
                                else -> RoundedCornerShape(0.dp)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = i.toString(),
                        fontSize = 16.sp,
                        fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Normal,
                        color = Color(0xFF0F1216),
                        textAlign = TextAlign.Center
                    )
                }
                if (i < 4) {
                    Box(
                        modifier = Modifier
                            .width(0.8.dp)
                            .fillMaxHeight()
                            .background(Color(0xFFBFC5CA))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        CustomDropdown(
            label = "¿Qué tipo de instalación de ordeñe tenés?",
            placeholder = "Seleccioná",
            items = instalacionesMock,
            selectedItem = tipoInstalacion,
            onItemSelected = { tipoInstalacion = it },
            enabled = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "¿Cuántos litros producís en promedio por día?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = litrosPromedio,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                    litrosPromedio = newValue
                }
                val litros = newValue.toIntOrNull() ?: 0
                if (newValue.isEmpty() || litros >= 2001) {
                    onStatusChanged(ProductionStatus.NORMAL)
                }
            },
            placeholder = {
                Text(
                    text = "000",
                    fontSize = 16.sp,
                    color = Color(0xFF555B63)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF0F1216)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(49.6.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBFC5CA),
                unfocusedBorderColor = Color(0xFFBFC5CA),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        CustomDropdown(
            label = "¿Cuál es el destino del producto?",
            placeholder = "Seleccioná",
            items = destinosMock,
            selectedItem = destinoProducto,
            onItemSelected = { destinoProducto = it },
            enabled = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "¿Cuál es el DEL promedio de tu rodeo lechero hoy?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "*Recordá que un rodeo eficiente suele promediar entre 150 y 180 días.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
            color = Color(0xFF555B63)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = delPromedio,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                    delPromedio = newValue
                }
            },
            placeholder = {
                Text(text = "000", fontSize = 16.sp, color = Color(0xFF555B63))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontSize = 16.sp, color = Color(0xFF0F1216)),
            modifier = Modifier
                .fillMaxWidth()
                .height(49.6.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBFC5CA),
                unfocusedBorderColor = Color(0xFFBFC5CA),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "¿Cuál es tu precio de venta actual por litro ($/Lts)?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = precioVenta,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    precioVenta = newValue
                }
            },
            placeholder = {
                Text(text = "$ 0.00", fontSize = 16.sp, color = Color(0xFF555B63))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = TextStyle(fontSize = 16.sp, color = Color(0xFF0F1216)),
            modifier = Modifier
                .fillMaxWidth()
                .height(49.6.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBFC5CA),
                unfocusedBorderColor = Color(0xFFBFC5CA),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        val litros = litrosPromedio.toIntOrNull() ?: 0
        if (litrosPromedio.isNotEmpty() && litros < 2001) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "¿Querés registrar tu rodeo?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                color = Color(0xFF0F1216)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onStatusChanged(ProductionStatus.ACCEPTED) }
                        .padding(end = 8.dp)
                ) {
                    CustomRadioButton(selected = productionStatus == ProductionStatus.ACCEPTED)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Aceptar",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 22.5.sp,
                        color = Color(0xFF0F172B)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onStatusChanged(ProductionStatus.CANCELED) }
                        .padding(end = 8.dp)
                ) {
                    CustomRadioButton(selected = productionStatus == ProductionStatus.CANCELED)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Cancelar",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 22.5.sp,
                        color = Color(0xFF0F172B)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ProductionStep() {
    var expandedCategory by remember { mutableStateOf("Rodeo Alto") }
    val mockBreedList = listOf("Holando Argentino", "Jersey", "Angus", "Hereford", "Cruza")

    // Estados para Rodeo Alto
    var altoAnimalCount by remember { mutableStateOf("") }
    var altoRationCost by remember { mutableStateOf("") }
    var altoSelectedBreed by remember { mutableStateOf<String?>(null) }
    var altoBreedCount by remember { mutableStateOf("") }

    // Estados para Rodeo Bajo
    var bajoAnimalCount by remember { mutableStateOf("") }
    var bajoRationCost by remember { mutableStateOf("") }
    var bajoSelectedBreed by remember { mutableStateOf<String?>(null) }
    var bajoBreedCount by remember { mutableStateOf("") }

    // Estados para Rodeo en Seco
    var secoAnimalCount by remember { mutableStateOf("") }
    var secoRationCost by remember { mutableStateOf("") }
    var secoSelectedBreed by remember { mutableStateOf<String?>(null) }
    var secoBreedCount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RodeoCategoryCard(
            title = "Rodeo Alto",
            subtitle = if (altoAnimalCount.isEmpty()) "Sin datos" else "$altoAnimalCount animales",
            animalCount = altoAnimalCount,
            onAnimalCountChange = { altoAnimalCount = it },
            rationCost = altoRationCost,
            onRationCostChange = { altoRationCost = it },
            breedOptions = mockBreedList,
            selectedBreed = altoSelectedBreed,
            onBreedSelected = { altoSelectedBreed = it },
            breedCount = altoBreedCount,
            onBreedCountChange = { altoBreedCount = it },
            onAddBreedClick = { breed, count ->
                println("Alto - Raza: $breed, Cant: $count")
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
            onAnimalCountChange = { bajoAnimalCount = it },
            rationCost = bajoRationCost,
            onRationCostChange = { bajoRationCost = it },
            breedOptions = mockBreedList,
            selectedBreed = bajoSelectedBreed,
            onBreedSelected = { bajoSelectedBreed = it },
            breedCount = bajoBreedCount,
            onBreedCountChange = { bajoBreedCount = it },
            onAddBreedClick = { breed, count ->
                println("Bajo - Raza: $breed, Cant: $count")
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
            onAnimalCountChange = { secoAnimalCount = it },
            rationCost = secoRationCost,
            onRationCostChange = { secoRationCost = it },
            breedOptions = mockBreedList,
            selectedBreed = secoSelectedBreed,
            onBreedSelected = { secoSelectedBreed = it },
            breedCount = secoBreedCount,
            onBreedCountChange = { secoBreedCount = it },
            onAddBreedClick = { breed, count ->
                println("Seco - Raza: $breed, Cant: $count")
            },
            isExpanded = expandedCategory == "Rodeo en Seco",
            onExpandClick = {
                expandedCategory = if (expandedCategory == "Rodeo en Seco") "" else "Rodeo en Seco"
            }
        )
    }
}
