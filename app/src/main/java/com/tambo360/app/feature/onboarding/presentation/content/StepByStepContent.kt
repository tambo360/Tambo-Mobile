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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tambo360.app.feature.onboarding.data.dto.request.QuestionnaireRequest
import com.tambo360.app.feature.onboarding.data.dto.request.RazaRequest
import com.tambo360.app.feature.onboarding.data.dto.request.UbicacionRequest
import com.tambo360.app.feature.onboarding.presentation.components.CustomDropdown
import com.tambo360.app.feature.onboarding.presentation.components.CustomRadioButton
import com.tambo360.app.feature.onboarding.presentation.components.RodeoCategoryCard
import com.tambo360.app.feature.onboarding.presentation.components.StepsProgress
import com.tambo360.app.feature.onboarding.presentation.util.BreedManagerUtil
import com.tambo360.app.feature.onboarding.presentation.util.QuestionnaireBuilderUtil
import com.tambo360.app.feature.onboarding.presentation.util.orEmptyString
import com.tambo360.app.feature.onboarding.presentation.util.orZero
import com.tambo360.app.feature.onboarding.presentation.util.toDoubleOrDefault
import com.tambo360.app.feature.onboarding.presentation.util.toIntOrDefault
import com.tambo360.app.feature.onboarding.presentation.viewmodel.OnboardingViewModel

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
fun StepByStepContent(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinishClick: (QuestionnaireRequest) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var currentStep by remember { mutableStateOf(ConfigStep.LOCATION) }
    val totalSteps = 3
    val scrollState = rememberScrollState()
    var productionStatus by remember { mutableStateOf(ProductionStatus.NORMAL) }

    var selectedProvince by remember { mutableStateOf<String?>(null) }
    var selectedLocation by remember { mutableStateOf<String?>(null) }
    var milksPerDay by remember { mutableStateOf<Int?>(null) }
    var installationType by remember { mutableStateOf<String?>(null) }
    var averageLiters by remember { mutableStateOf("") }
    var milkDestination by remember { mutableStateOf<String?>(null) }
    var averageDEL by remember { mutableStateOf("") }
    var salePrice by remember { mutableStateOf("") }

    var altoAnimalCount by remember { mutableStateOf("") }
    var altoRationCost by remember { mutableStateOf("") }
    val altoRazas = remember { mutableStateListOf<RazaRequest>() }

    var bajoAnimalCount by remember { mutableStateOf("") }
    var bajoRationCost by remember { mutableStateOf("") }
    val bajoRazas = remember { mutableStateListOf<RazaRequest>() }

    var secoAnimalCount by remember { mutableStateOf("") }
    var secoRationCost by remember { mutableStateOf("") }
    val secoRazas = remember { mutableStateListOf<RazaRequest>() }

    var selectedProvinceId by remember { mutableStateOf<String?>(null) }
    var selectedProvinceName by remember { mutableStateOf<String?>(null) }
    var selectedLocationName by remember { mutableStateOf<String?>(null) }
    val provinciasNombres = uiState.provincias.map { it.nombre }
    val localidadesNombres = uiState.localidades.map { it.nombre }

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
                ConfigStep.LOCATION -> {
                    LocationStep(
                        provinciasList = provinciasNombres,
                        localidadesList = localidadesNombres,
                        selectedProvince = selectedProvinceName,
                        onProvinceChanged = { provinceName ->
                            selectedProvinceName = provinceName
                            selectedLocationName = null
                            val provinciaSeleccionada = uiState.provincias.find { it.nombre == provinceName }
                            if (provinciaSeleccionada != null) {
                                selectedProvinceId = provinciaSeleccionada.id
                                viewModel.onProvinciaSelected(provinciaSeleccionada)
                            }
                        },
                        selectedLocation = selectedLocationName,
                        onLocationChanged = { selectedLocationName = it },
                        isLoadingLocations = uiState.loading
                    )
                }

                ConfigStep.ORDENE -> {
                    OrdeneStep(
                        productionStatus = productionStatus,
                        onStatusChanged = { productionStatus = it },
                        milksPerDay = milksPerDay,
                        onMilksPerDayChanged = { milksPerDay = it },
                        installationType = installationType,
                        onInstallationTypeChanged = { installationType = it },
                        averageLiters = averageLiters,
                        onAverageLitersChanged = { averageLiters = it },
                        milkDestination = milkDestination,
                        onMilkDestinationChanged = { milkDestination = it },
                        averageDEL = averageDEL,
                        onAverageDELChanged = { averageDEL = it },
                        salePrice = salePrice,
                        onSalePriceChanged = { salePrice = it }
                    )
                }

                ConfigStep.PRODUCTION -> {
                    when (productionStatus) {
                        ProductionStatus.ACCEPTED -> {}
                        ProductionStatus.CANCELED -> {}
                        ProductionStatus.NORMAL -> {
                            ProductionStep(
                                altoAnimalCount = altoAnimalCount,
                                onAltoAnimalCountChange = { altoAnimalCount = it },
                                altoRationCost = altoRationCost,
                                onAltoRationCostChange = { altoRationCost = it },
                                onAddAltoBreed = { raza, cant ->
                                    BreedManagerUtil.processAndAddBreed(raza, cant, altoRazas)
                                },
                                bajoAnimalCount = bajoAnimalCount,
                                onBajoAnimalCountChange = { bajoAnimalCount = it },
                                bajoRationCost = bajoRationCost,
                                onBajoRationCostChange = { bajoRationCost = it },
                                onAddBajoBreed = { raza, cant ->
                                    BreedManagerUtil.processAndAddBreed(raza, cant, bajoRazas)
                                },
                                secoAnimalCount = secoAnimalCount,
                                onSecoAnimalCountChange = { secoAnimalCount = it },
                                secoRationCost = secoRationCost,
                                onSecoRationCostChange = { secoRationCost = it },
                                onAddSecoBreed = { raza, cant ->
                                    BreedManagerUtil.processAndAddBreed(raza, cant, secoRazas)
                                }
                            )
                        }
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
                            val trackingType =
                                QuestionnaireBuilderUtil.getTrackingType(productionStatus)

                            val herdList = QuestionnaireBuilderUtil.buildHerdList(
                                trackingType = trackingType,
                                highRationCost = altoRationCost,
                                highBreeds = altoRazas,
                                lowRationCost = bajoRationCost,
                                lowBreeds = bajoRazas,
                                dryRationCost = secoRationCost,
                                dryBreeds = secoRazas
                            )
                            val request = QuestionnaireRequest(
                                tipoSeguimiento = trackingType.apiValue,
                                cantOrdenie = milksPerDay.orZero(),
                                tipoOrdenie = installationType.orEmptyString(),
                                promDEL = averageDEL.toIntOrDefault(),
                                promLitros = averageLiters.toDoubleOrDefault(),
                                ventaLeche = milkDestination.orEmptyString(),
                                precioLitro = salePrice.toDoubleOrDefault(),
                                ubicacion = UbicacionRequest(
                                    provincia = selectedProvince.orEmptyString(),
                                    localidad = selectedLocation.orEmptyString()
                                ),
                                rodeos = herdList,
                                animales = null
                            )
                            onFinishClick(request)
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
fun LocationStep(
    provinciasList: List<String>,
    localidadesList: List<String>,
    selectedProvince: String?,
    onProvinceChanged: (String?) -> Unit,
    selectedLocation: String?,
    onLocationChanged: (String?) -> Unit,
    isLoadingLocations: Boolean
) {
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
            items = provinciasList,
            selectedItem = selectedProvince,
            onItemSelected = onProvinceChanged,
            enabled = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (isLoadingLocations && selectedProvince != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color(0xFF236E44)
                )
            }
        } else {
            CustomDropdown(
                label = "Localidad",
                subLabel = if (selectedProvince == null) "Elegí primero la provincia." else "",
                placeholder = if (localidadesList.isEmpty() && selectedProvince != null) "Sin localidades" else "Seleccioná",
                items = localidadesList,
                selectedItem = selectedLocation,
                onItemSelected = onLocationChanged,
                enabled = selectedProvince != null && localidadesList.isNotEmpty()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun OrdeneStep(
    productionStatus: ProductionStatus,
    onStatusChanged: (ProductionStatus) -> Unit,
    milksPerDay: Int?,
    onMilksPerDayChanged: (Int?) -> Unit,
    installationType: String?,
    onInstallationTypeChanged: (String?) -> Unit,
    averageLiters: String,
    onAverageLitersChanged: (String) -> Unit,
    milkDestination: String?,
    onMilkDestinationChanged: (String?) -> Unit,
    averageDEL: String,
    onAverageDELChanged: (String) -> Unit,
    salePrice: String,
    onSalePriceChanged: (String) -> Unit
) {
    val installationMocks =
        listOf("balde", "linea", "espina_de_pescado", "rotativo", "manual", "otro")
    val destinationMocks =
        listOf("USINA", "COOPERTIVA", "ELABORACION_PROPIA", "VENTA_DIRECTA_MERCADO_LOCAL")

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
                val isSelected = milksPerDay == i
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onMilksPerDayChanged(i) }
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
            items = installationMocks,
            selectedItem = installationType,
            onItemSelected = onInstallationTypeChanged,
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
            value = averageLiters,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                    onAverageLitersChanged(newValue)
                }
                val liters = newValue.toIntOrNull() ?: 0
                if (newValue.isEmpty() || liters >= 2001) {
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
            items = destinationMocks,
            selectedItem = milkDestination,
            onItemSelected = onMilkDestinationChanged,
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
            value = averageDEL,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                    onAverageDELChanged(newValue)
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
            value = salePrice,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    onSalePriceChanged(newValue)
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
        val liters = averageLiters.toIntOrNull() ?: 0
        if (averageLiters.isNotEmpty() && liters < 2001) {
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
    val mockBreedList = listOf("HOLANDO_ARGENTINO", "JERSEY", "ANGUS", "HEREFORD", "CRUZA")
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
