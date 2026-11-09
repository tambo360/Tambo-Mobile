package com.tambo360.app.feature.onboarding.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tambo360.app.R
import com.tambo360.app.feature.onboarding.presentation.util.EstablishmentConstants
import com.tambo360.app.feature.onboarding.presentation.viewmodel.OnboardingViewModel
import com.tambo360.app.feature.onboarding.presentation.viewmodel.states.OnboardingUiState

@Composable
fun CreateEstablishmentContent(
    goToStep : () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    var currentStep by remember { mutableIntStateOf(EstablishmentConstants.WELCOME) }
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(state) {
        state.data?.let {
            if (it.success) {
                currentStep = EstablishmentConstants.CREATED
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.register_image),
            contentDescription = "Fondo de pantalla",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 16.dp)
                .verticalScroll(scrollState),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (currentStep) {
                        EstablishmentConstants.WELCOME -> {
                            WelcomeStep(
                                onGoToCreate = { currentStep = EstablishmentConstants.CREATE }
                            )
                        }

                        EstablishmentConstants.CREATE -> {
                            CreateStep(
                                state = state,
                                onEstablishmentCreated = { name ->
                                    viewModel.createOrganization(name = name)
                                }
                            )
                        }

                        EstablishmentConstants.CREATED -> {
                            CreatedStep(
                                onFinish = goToStep
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeStep(
    onGoToCreate: () -> Unit
) {
    Image(
        painter = painterResource(id = R.drawable.ic_tambo),
        contentDescription = "Logo Tambo",
        modifier = Modifier.size(120.dp)
    )
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = "¡Bienvenido a\nTambo360!",
        fontWeight = FontWeight.W700,
        fontSize = 26.sp,
        lineHeight = 36.sp,
        color = Color(0xFF171717),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Para comenzar, necesitas crear\ntu primarea organización\no esperar a ser invitado por\nalguien más",
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        color = Color(0xFF000000),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(32.dp))
    Button(
        onClick = onGoToCreate,
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29845A)),
        contentPadding = PaddingValues(start = 19.dp, top = 4.dp, end = 20.dp, bottom = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.add_circle),
                contentDescription = "Crear",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Crear establecimiento",
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun CreateStep(
    state: OnboardingUiState,
    onEstablishmentCreated: (String) -> Unit
) {
    var establishmentName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear nuevo",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.5.sp,
            color = Color(0xFF0F1216)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.width(273.dp)
        ) {
            Text(
                text = "Nombre del establecimiento",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                color = Color(0xFF0F1216)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = establishmentName,
                onValueChange = { establishmentName = it },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(8.dp),
                        ambientColor = Color.Black.copy(alpha = 0.05f),
                        spotColor = Color.Black.copy(alpha = 0.05f)
                    ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color(0xFFBFC5CA),
                    unfocusedBorderColor = Color(0xFFBFC5CA),
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { onEstablishmentCreated(establishmentName) },
            modifier = Modifier
                .width(271.dp)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF29845A)
            ),
            enabled = !state.loading
        ) {
            if (state.loading) {
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = "Aceptar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CreatedStep(
    onFinish: () -> Unit
) {
    Box(
        modifier = Modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.success_icon),
            contentDescription = "Éxito",
            modifier = Modifier.fillMaxSize()
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = "¡Establecimiento creado\ncorrectamente!",
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp,
        color = Color(0xFF252525),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Ya puedes comenzar a\nconfigurar tu\nestablecimiento",
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        color = Color(0xFF4B5563),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        onClick = onFinish,
        modifier = Modifier
            .width(182.29.dp)
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF29845A)
        ),
        contentPadding = PaddingValues(
            start = 32.dp,
            top = 16.dp,
            end = 32.dp,
            bottom = 16.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Continuar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                color = Color(0xFFFFFBF1)
            )
            Icon(
                painter = painterResource(R.drawable.arrowright),
                contentDescription = "Continuar",
                tint = Color(0xFFFFFBF1),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
