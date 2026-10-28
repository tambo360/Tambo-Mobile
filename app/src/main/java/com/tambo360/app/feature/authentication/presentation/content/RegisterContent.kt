package com.tambo360.app.feature.authentication.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R
import com.tambo360.app.core.ui.components.ErrorMessage
import com.tambo360.app.feature.authentication.presentation.components.ConfirmPasswordField
import com.tambo360.app.feature.authentication.presentation.components.EmailField
import com.tambo360.app.feature.authentication.presentation.components.NameField
import com.tambo360.app.feature.authentication.presentation.components.PasswordField
import com.tambo360.app.feature.authentication.presentation.viewmodel.RegisterUiState

@Composable
fun RegisterContent(
    state: RegisterUiState,
    onRegisterClick: (String, String, String) -> Unit
) {

    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                .padding(horizontal = 32.dp, vertical = 16.dp)
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
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logotipo),
                        contentDescription = "Logo Tambo",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Crear cuenta",
                        style = TextStyle(
                            fontWeight = FontWeight.W700,
                            fontSize = 28.sp,
                            lineHeight = 30.sp,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Comencemos con el proceso de registro \npara tu establecimiento lácteo",
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            color = Color(0xFF4B5563),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NameField(
                        value = name,
                        onValueChange = {
                            name = it
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    EmailField(
                        value = email,
                        onValueChange = {
                            email = it
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    PasswordField(
                        value = password,
                        onValueChange = {
                            password = it
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Requisitos: ")
                            }
                            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                                append("8 caracteres, mayúscula (A-Z), minúscula (a-z) y carácter especial.")
                            }
                        },
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            color = Color(0xFF4B5563)
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    ConfirmPasswordField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                        }
                    )
                    if (state.error.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        ErrorMessage(message = state.error)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onRegisterClick(name, email, password)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0B1001)
                        ),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        if (state.loading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Siguiente",
                                    style = TextStyle(
                                        fontWeight = FontWeight.W700,
                                        fontSize = 16.sp,
                                        lineHeight = 24.sp,
                                        color = Color(0xFFFFFBF1)
                                    )
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.arrowright),
                                    contentDescription = "",
                                    tint = Color(0xFFFFFBF1)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.W400)) {
                                append("¿Ya tenes una cuenta? ")
                            }
                            withStyle(SpanStyle(fontWeight = FontWeight.W700)) {
                                append("Inicia sesion")
                            }
                        },
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = Color(0xFF252525),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(8.dp)
                    )
                }
            }
        }
    }

}
