package com.tambo360.app.feature.onboarding.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R

@Composable
fun ProductionStepAccepted(
    animalesCount: Int,
    codigoAnimal: String,
    onCodigoAnimalChange: (String) -> Unit,
    nombreAnimal: String,
    onNombreAnimalChange: (String) -> Unit,
    selectedRaza: String?,
    onRazaSelected: (String) -> Unit,
    selectedCategoria: String?,
    onCategoriaSelected: (String) -> Unit,
    selectedEstado: String?,
    onEstadoSelected: (String) -> Unit,
    onAddAnimal: () -> Unit
) {
    val razaOptions = listOf(
        "HOLANDO_ARGENTINO", "JERSEY", "PARDO_SUIZO", "GIR_LECHERO", "HOLANDO_JERSEY_CRUZA",
        "AYRSHIRE", "NORMANDO", "BROWN_SWISS", "MONTBELIARDE", "SIMMENTAL_LECHERO", "OTRAS"
    )
    val categoriaOptions = listOf("ORDENE", "SECAS")
    val estadoOptions = listOf("MASTITIS", "TRATAMIENTO", "PREPARTO", "SANO")

    val nextAnimalNumber = animalesCount + 1
    val isAddAnimalEnabled = codigoAnimal.isNotBlank() &&
            nombreAnimal.isNotBlank() &&
            !selectedRaza.isNullOrBlank() &&
            !selectedCategoria.isNullOrBlank() &&
            !selectedEstado.isNullOrBlank()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Registro Individual de Animales",
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 30.sp,
            color = Color(0xFF0F1216)
        )
        Text(
            text = "Animales registrados: $animalesCount",
            fontSize = 14.sp,
            color = Color(0xFF555B63)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(0.8.dp, Color(0xFFBFC5CA))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Animal $nextAnimalNumber",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    color = Color(0xFF0F1216)
                )

                CustomInputIndividual(
                    label = "RP/Nº",
                    value = codigoAnimal,
                    onValueChange = onCodigoAnimalChange,
                    placeholder = "001",
                    keyboardType = KeyboardType.Number
                )

                CustomInputIndividual(
                    label = "Nombre",
                    value = nombreAnimal,
                    onValueChange = onNombreAnimalChange,
                    placeholder = "Margarita"
                )

                Column {
                    Text(
                        text = "Raza",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0F1216)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomDropdown(
                        label = "",
                        placeholder = "Seleccioná",
                        items = razaOptions,
                        selectedItem = selectedRaza,
                        onItemSelected = onRazaSelected,
                        enabled = true
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Categoría",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF0F1216)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomDropdown(
                            label = "",
                            placeholder = "Seleccioná",
                            items = categoriaOptions,
                            selectedItem = selectedCategoria,
                            onItemSelected = onCategoriaSelected,
                            enabled = true
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Estado",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF0F1216)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomDropdown(
                            label = "",
                            placeholder = "Seleccioná",
                            items = estadoOptions,
                            selectedItem = selectedEstado,
                            onItemSelected = onEstadoSelected,
                            enabled = true
                        )
                    }
                }
            }
        }

        OutlinedButton(
            onClick = onAddAnimal,
            enabled = isAddAnimalEnabled,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                width = 1.dp,
                color = if (isAddAnimalEnabled) Color(0xFF236E44) else Color(0xFFE5E7EB)
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF236E44),
                disabledContentColor = Color(0xFFA0AAB5)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = "Agregar"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Agregar animal",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun CustomInputIndividual(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = Int.MAX_VALUE
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(49.6.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(0.8.dp, Color(0xFFBFC5CA), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF555B63)
                )
            }
            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= maxLength) {
                        val isValid = when (keyboardType) {
                            KeyboardType.Number -> newValue.all { it.isDigit() }
                            else -> true
                        }
                        if (isValid) {
                            onValueChange(newValue)
                        }
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF0F1216)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
