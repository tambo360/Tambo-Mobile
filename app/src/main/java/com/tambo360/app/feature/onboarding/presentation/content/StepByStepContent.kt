package com.tambo360.app.feature.onboarding.presentation.content

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.tambo360.app.feature.onboarding.data.dto.request.AnimalRequest
import com.tambo360.app.feature.onboarding.data.dto.request.QuestionnaireRequest
import com.tambo360.app.feature.onboarding.presentation.components.CustomDropdown
import com.tambo360.app.feature.onboarding.presentation.components.CustomRadioButton
import com.tambo360.app.feature.onboarding.presentation.components.IconBackWithText
import com.tambo360.app.feature.onboarding.presentation.components.ProductionStep
import com.tambo360.app.feature.onboarding.presentation.components.ProductionStepAccepted
import com.tambo360.app.feature.onboarding.presentation.components.ProductionStepCancelled
import com.tambo360.app.feature.onboarding.presentation.components.StepsProgress
import com.tambo360.app.feature.onboarding.presentation.util.BreedManagerUtil
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
    viewModel: OnboardingViewModel,
    onFinishClick: (QuestionnaireRequest) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val form = uiState.formData
    val scrollState = rememberScrollState()
    val stepTitle = when (uiState.currentStep) {
        ConfigStep.LOCATION -> null
        ConfigStep.ORDENE -> "Ordeñe"
        ConfigStep.PRODUCTION -> "Registro de animales"
    }
    val isFinishingLoading = uiState.currentStep == ConfigStep.PRODUCTION && uiState.loading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF9FAFC))
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        if (stepTitle != null) {
            IconBackWithText(
                title = stepTitle,
                onBackClick = { viewModel.onPreviousStep() }
            )
        }
        StepsProgress(
            currentStep = uiState.currentStep.stepNumber,
            totalSteps = 3,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            when (uiState.currentStep) {
                ConfigStep.LOCATION -> {
                    LocationStep(
                        provinciasList = uiState.provincias.map { it.nombre },
                        localidadesList = uiState.localidades.map { it.nombre },
                        selectedProvince = uiState.selectedProvincia?.nombre,
                        onProvinceChanged = { provinceName ->
                            val prov = uiState.provincias.find { it.nombre == provinceName }
                            if (prov != null) viewModel.onProvinciaSelected(prov)
                        },
                        selectedLocation = form.selectedLocationName,
                        onLocationChanged = { loc ->
                            viewModel.updateFormData {
                                it.copy(
                                    selectedLocationName = loc
                                )
                            }
                        },
                        isLoadingLocations = uiState.loading
                    )
                }

                ConfigStep.ORDENE -> {
                    OrdeneStep(
                        productionStatus = form.productionStatus,
                        onStatusChanged = { st ->
                            viewModel.updateFormData {
                                it.copy(
                                    productionStatus = st
                                )
                            }
                        },
                        milksPerDay = form.milksPerDay,
                        onMilksPerDayChanged = { m -> viewModel.updateFormData { it.copy(milksPerDay = m) } },
                        installationType = form.installationType,
                        onInstallationTypeChanged = { i ->
                            viewModel.updateFormData {
                                it.copy(
                                    installationType = i
                                )
                            }
                        },
                        averageLiters = form.averageLiters,
                        onAverageLitersChanged = { l ->
                            viewModel.updateFormData {
                                it.copy(
                                    averageLiters = l
                                )
                            }
                        },
                        milkDestination = form.milkDestination,
                        onMilkDestinationChanged = { d ->
                            viewModel.updateFormData {
                                it.copy(
                                    milkDestination = d
                                )
                            }
                        },
                        averageDEL = form.averageDEL,
                        onAverageDELChanged = { d -> viewModel.updateFormData { it.copy(averageDEL = d) } },
                        salePrice = form.salePrice,
                        onSalePriceChanged = { p -> viewModel.updateFormData { it.copy(salePrice = p) } }
                    )
                }

                ConfigStep.PRODUCTION -> {
                    when (form.productionStatus) {
                        ProductionStatus.ACCEPTED -> {
                            ProductionStepAccepted(
                                animalesCount = form.individualAnimales.size,
                                codigoAnimal = form.tempAnimalCodigo,
                                onCodigoAnimalChange = { c ->
                                    viewModel.updateFormData {
                                        it.copy(
                                            tempAnimalCodigo = c
                                        )
                                    }
                                },
                                nombreAnimal = form.tempAnimalNombre,
                                onNombreAnimalChange = { n ->
                                    viewModel.updateFormData {
                                        it.copy(
                                            tempAnimalNombre = n
                                        )
                                    }
                                },
                                selectedRaza = form.tempAnimalSelectedRaza,
                                onRazaSelected = { r ->
                                    viewModel.updateFormData {
                                        it.copy(
                                            tempAnimalSelectedRaza = r
                                        )
                                    }
                                },
                                selectedCategoria = form.tempAnimalSelectedCategoria,
                                onCategoriaSelected = { c ->
                                    viewModel.updateFormData {
                                        it.copy(
                                            tempAnimalSelectedCategoria = c
                                        )
                                    }
                                },
                                selectedEstado = form.tempAnimalSelectedEstado,
                                onEstadoSelected = { e ->
                                    viewModel.updateFormData {
                                        it.copy(
                                            tempAnimalSelectedEstado = e
                                        )
                                    }
                                },
                                onAddAnimal = {
                                    viewModel.updateFormData { current ->
                                        if (current.tempAnimalCodigo.isNotBlank() &&
                                            current.tempAnimalNombre.isNotBlank() &&
                                            current.tempAnimalSelectedRaza != null &&
                                            current.tempAnimalSelectedCategoria != null &&
                                            current.tempAnimalSelectedEstado != null
                                        ) {
                                            val newAnimal = AnimalRequest(
                                                codigo = current.tempAnimalCodigo.trim(),
                                                nombre = current.tempAnimalNombre.trim(),
                                                raza = current.tempAnimalSelectedRaza,
                                                categoria = current.tempAnimalSelectedCategoria,
                                                estado = current.tempAnimalSelectedEstado
                                            )
                                            current.copy(
                                                individualAnimales = current.individualAnimales + newAnimal,
                                                tempAnimalCodigo = "",
                                                tempAnimalNombre = "",
                                                tempAnimalSelectedRaza = null,
                                                tempAnimalSelectedCategoria = null,
                                                tempAnimalSelectedEstado = null
                                            )
                                        } else {
                                            current
                                        }
                                    }
                                }
                            )
                        }

                        ProductionStatus.CANCELED -> {
                            ProductionStepCancelled(
                                unicoAnimalCount = form.unicoAnimalCount,
                                onUnicoAnimalCountChange = { v ->
                                    viewModel.updateFormData { it.copy(unicoAnimalCount = v) }
                                },
                                unicoRationCost = form.unicoRationCost,
                                onUnicoRationCostChange = { v ->
                                    viewModel.updateFormData { it.copy(unicoRationCost = v) }
                                },
                                onAddUnicoBreed = { r, c ->
                                    viewModel.updateFormData { current ->
                                        val list = current.unicoRazas.toMutableList()
                                        BreedManagerUtil.processAndAddBreed(r, c, list)
                                        current.copy(
                                            unicoRazas = list,
                                            savedUnicoRationCost = current.unicoRationCost.ifBlank { current.savedUnicoRationCost },
                                            unicoAnimalCount = "",
                                            unicoRationCost = ""
                                        )
                                    }
                                },
                                secaAnimalCount = form.unicaSecaAnimalCount,
                                onSecaAnimalCountChange = { v ->
                                    viewModel.updateFormData { it.copy(unicaSecaAnimalCount = v) }
                                },
                                secaRationCost = form.unicaSecaRationCost,
                                onSecaRationCostChange = { v ->
                                    viewModel.updateFormData { it.copy(unicaSecaRationCost = v) }
                                },
                                onAddSecaBreed = { r, c ->
                                    viewModel.updateFormData { current ->
                                        val list = current.unicaSecaRazas.toMutableList()
                                        BreedManagerUtil.processAndAddBreed(r, c, list)
                                        current.copy(
                                            unicaSecaRazas = list,
                                            savedUnicaSecaRationCost = current.unicaSecaRationCost.ifBlank { current.savedUnicaSecaRationCost },
                                            unicaSecaAnimalCount = "",
                                            unicaSecaRationCost = ""
                                        )
                                    }
                                }
                            )
                        }

                        ProductionStatus.NORMAL -> {
                            ProductionStep(
                                altoAnimalCount = form.altoAnimalCount,
                                onAltoAnimalCountChange = { v ->
                                    viewModel.updateFormData { it.copy(altoAnimalCount = v) }
                                },
                                altoRationCost = form.altoRationCost,
                                onAltoRationCostChange = { v ->
                                    viewModel.updateFormData { it.copy(altoRationCost = v) }
                                },
                                onAddAltoBreed = { r, c ->
                                    viewModel.updateFormData { current ->
                                        val list = current.altoRazas.toMutableList()
                                        BreedManagerUtil.processAndAddBreed(r, c, list)
                                        current.copy(
                                            altoRazas = list,
                                            savedAltoRationCost = current.altoRationCost.ifBlank { current.savedAltoRationCost },
                                            altoAnimalCount = "",
                                            altoRationCost = ""
                                        )
                                    }
                                },
                                bajoAnimalCount = form.bajoAnimalCount,
                                onBajoAnimalCountChange = { v ->
                                    viewModel.updateFormData { it.copy(bajoAnimalCount = v) }
                                },
                                bajoRationCost = form.bajoRationCost,
                                onBajoRationCostChange = { v ->
                                    viewModel.updateFormData { it.copy(bajoRationCost = v) }
                                },
                                onAddBajoBreed = { r, c ->
                                    viewModel.updateFormData { current ->
                                        val list = current.bajoRazas.toMutableList()
                                        BreedManagerUtil.processAndAddBreed(r, c, list)
                                        current.copy(
                                            bajoRazas = list,
                                            savedBajoRationCost = current.bajoRationCost.ifBlank { current.savedBajoRationCost },
                                            bajoAnimalCount = "",
                                            bajoRationCost = ""
                                        )
                                    }
                                },
                                secoAnimalCount = form.secoAnimalCount,
                                onSecoAnimalCountChange = { v ->
                                    viewModel.updateFormData { it.copy(secoAnimalCount = v) }
                                },
                                secoRationCost = form.secoRationCost,
                                onSecoRationCostChange = { v ->
                                    viewModel.updateFormData { it.copy(secoRationCost = v) }
                                },
                                onAddSecoBreed = { r, c ->
                                    viewModel.updateFormData { current ->
                                        val list = current.secoRazas.toMutableList()
                                        BreedManagerUtil.processAndAddBreed(r, c, list)
                                        current.copy(
                                            secoRazas = list,
                                            savedSecoRationCost = current.secoRationCost.ifBlank { current.savedSecoRationCost },
                                            secoAnimalCount = "",
                                            secoRationCost = ""
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
        Button(
            onClick = {
                if (!uiState.loading) {
                    if (uiState.currentStep == ConfigStep.PRODUCTION) {
                        onFinishClick(viewModel.generateFinalRequest())
                    } else {
                        viewModel.onNextStep()
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF236E44),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFE5E7EB),
                disabledContentColor = Color(0xFFA0AAB5)
            ),
            enabled = uiState.isNextEnabled && !uiState.loading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp)
        ) {
            if (isFinishingLoading) {
                CircularProgressIndicator(
                    color = Color(0xFF236E44),
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = if (uiState.currentStep == ConfigStep.PRODUCTION) "Finalizar" else "Siguiente",
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
                if (newValue.length <= 8 && (newValue.isEmpty() || newValue.all { it.isDigit() })) {
                    onAverageLitersChanged(newValue)
                    val liters = newValue.toIntOrNull() ?: 0
                    if (newValue.isEmpty() || liters >= 2001) {
                        onStatusChanged(ProductionStatus.NORMAL)
                    }
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
            text = "Recordá que un rodeo eficiente suele promediar entre 150 y 180 días.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
            color = Color(0xFF555B63)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = averageDEL,
            onValueChange = { newValue ->
                if (newValue.length <= 4 && (newValue.isEmpty() || newValue.all { it.isDigit() })) {
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
                if (newValue.length <= 8 && (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*\$")))) {
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
