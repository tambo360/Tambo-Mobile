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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tambo360.app.R
import com.tambo360.app.feature.authentication.presentation.components.ConfirmNewPasswordField
import com.tambo360.app.feature.authentication.presentation.components.NewPasswordField
import com.tambo360.app.feature.authentication.presentation.viewmodel.states.ResetPasswordUiState

@Composable
fun ChangePasswordContent(
    state: ResetPasswordUiState,
    goToLogin : () -> Unit,
    onUpdatePassword: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.register_image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 16.dp)
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
                    Image(
                        painter = painterResource(id = R.drawable.newlogo),
                        contentDescription = "",
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = "Actualizar contraseña",
                        color = Color(0xFF0B1001),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 32.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ingresa una nueva contraseña para tu cuenta",
                        color = Color(0xFF4B5563),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    NewPasswordField(
                        value = newPassword,
                        onValueChange = {
                            newPassword = it
                        }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    ConfirmNewPasswordField(
                        value = confirmNewPassword,
                        onValueChange = {
                            confirmNewPassword = it
                        }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = { onUpdatePassword(newPassword) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0B1001)),
                        shape = RoundedCornerShape(8.dp),
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
                                    text = "Actualizar contraseña",
                                    color = Color(0xFFFFFBF1),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W700,
                                    lineHeight = 24.sp
                                )
                                Icon(
                                    painter = painterResource(R.drawable.arrowright),
                                    contentDescription = "",
                                    tint = Color(0xFFFFFBF1)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Volver a Inicio de Sesión",
                        color = Color(0xFF0B1001),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = goToLogin)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
