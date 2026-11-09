package com.tambo360.app.feature.onboarding.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R

@Composable
fun RodeoCategoryCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    animalCount: String,
    onAnimalCountChange: (String) -> Unit,
    rationCost: String,
    onRationCostChange: (String) -> Unit,
    breedOptions: List<String>,
    selectedBreed: String?,
    onBreedSelected: (String) -> Unit,
    breedCount: String,
    onBreedCountChange: (String) -> Unit,
    onAddBreedClick: (breed: String, count: String) -> Unit,
    isExpanded: Boolean,
    onExpandClick: () -> Unit
) {
    val arrowRotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "ArrowRotation"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder().copy(
            width = 0.8.dp,
            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFBFC5CA))
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onExpandClick() }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 24.sp,
                        color = Color(0xFF0F1216)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        color = Color(0xFF555B63)
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.arrowright),
                    contentDescription = if (isExpanded) "Colapsar" else "Expandir",
                    modifier = Modifier.rotate(arrowRotation),
                    tint = Color(0xFF0F1216)
                )
            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CustomInputField(
                        label = "Cantidad de animales",
                        value = animalCount,
                        onValueChange = onAnimalCountChange,
                        placeholder = "000",
                        keyboardType = KeyboardType.Number
                    )

                    CustomInputField(
                        label = "Costo de Ración ($/cab/día)",
                        value = rationCost,
                        onValueChange = onRationCostChange,
                        placeholder = "$ 0.00",
                        keyboardType = KeyboardType.Decimal
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF0F3)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "¿Qué razas hay en este rodeo?",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 24.sp,
                                color = Color(0xFF0F1216)
                            )

                            CustomDropdown(
                                label = "Raza",
                                placeholder = "Seleccioná una raza",
                                items = breedOptions,
                                selectedItem = selectedBreed,
                                onItemSelected = onBreedSelected,
                                enabled = true
                            )

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Cantidad",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 24.sp,
                                    color = Color(0xFF0F1216)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CustomInputFieldContainer(
                                        modifier = Modifier.weight(1f),
                                        value = breedCount,
                                        onValueChange = onBreedCountChange,
                                        placeholder = "000",
                                        keyboardType = KeyboardType.Number
                                    )

                                    Button(
                                        onClick = {
                                            if (selectedBreed != null && breedCount.isNotBlank()) {
                                                onAddBreedClick(selectedBreed, breedCount)
                                            }
                                        },
                                        modifier = Modifier.height(48.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF236E44)
                                        )
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.arrowright),
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "Agregar",
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
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
        CustomInputFieldContainer(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            keyboardType = keyboardType
        )
    }
}

@Composable
private fun CustomInputFieldContainer(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
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
            onValueChange = onValueChange,
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
