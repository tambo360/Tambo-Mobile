package com.tambo360.app.feature.authentication.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.tambo360.app.feature.authentication.presentation.components.ResendEmailButton
import com.tambo360.app.feature.authentication.presentation.viewmodel.states.ForgotPasswordUiState
import com.tambo360.app.feature.authentication.presentation.viewmodel.states.TimerUiState

@Composable
fun ForgotCheckContent(
    email: String,
    state: ForgotPasswordUiState,
    timerState: TimerUiState,
    onResendClick: () -> Unit,
    onChangeEmail: () -> Unit
) {
    val scrollState = rememberScrollState()
    val buttonText = if (timerState.isRunning) {
        "Reenviar email (${timerState.formattedTime})"
    } else {
        "Reenviar email"
    }

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
                        text = "Revisa tu email",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 36.sp,
                        lineHeight = 50.sp,
                        color = Color(0xFF0B1001),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = "Enviamos un enlace  para restablecer tu contrasña  a $email Hacé click en el enlace para  cambiar tu clave.",
                        fontWeight = FontWeight.W400,
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = Color(0xFF626059),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ResendEmailButton(
                        text = buttonText,
                        loading = state.resendLoading,
                        enabled = !timerState.isRunning,
                        onClick = onResendClick
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedButton(
                        onClick = onChangeEmail,
                        modifier = Modifier
                            .width(345.dp)
                            .height(55.dp),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(
                            start = 32.dp,
                            top = 16.dp,
                            end = 32.dp,
                            bottom = 16.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF171717)
                        )
                    ) {
                        Text(
                            text = "Cambiar email",
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.W700,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
