package com.tambo360.app.core.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tambo360.app.core.ui.viewmodel.StartScreenViewModel
import com.tambo360.app.core.ui.viewmodel.UiState
import com.tambo360.app.feature.authentication.presentation.screen.ChangePasswordScreen
import com.tambo360.app.feature.authentication.presentation.screen.CheckEmailScreen
import com.tambo360.app.feature.authentication.presentation.screen.ForgotCheckScreen
import com.tambo360.app.feature.authentication.presentation.screen.LoginScreen
import com.tambo360.app.feature.authentication.presentation.screen.ForgotPasswordScreen
import com.tambo360.app.feature.authentication.presentation.screen.RegisterScreen
import com.tambo360.app.feature.onboarding.presentation.screen.OnboardingMainScreen

@Composable
fun AppScreen(
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val token by viewModel.tokenState.collectAsState()
    when (val currentState = state) {
        UiState.Login -> {
            LoginScreen(
                goToRecoverPassword = { viewModel.updateUiState(state = UiState.Forgot) },
                goToRegister = { viewModel.updateUiState(state = UiState.Register) },
                goTo = { viewModel.updateUiState(state = UiState.Home) }
            )
        }

        UiState.Register -> {
            RegisterScreen(
                goTo = { email ->
                    viewModel.updateUiState(state = UiState.CheckEmail(email))
                },
                onClickBack = {
                    viewModel.updateUiState(state = UiState.Login)
                }
            )
        }

        is UiState.CheckEmail -> {
            CheckEmailScreen(
                email = currentState.email,
                token = token,
                onChangeEmail = {
                    viewModel.updateUiState(state = UiState.Register)
                },
                goToOnboarding = {
                    viewModel.updateUiState(state = UiState.Onboarding)
                }
            )
        }

        UiState.Forgot -> {
            ForgotPasswordScreen(
                goBack = {
                    viewModel.updateUiState(state = UiState.Login)
                },
                goTo = { email ->
                    viewModel.updateUiState(state = UiState.ForgotCheck(email = email))
                }
            )
        }

         is UiState.ForgotCheck -> {
             ForgotCheckScreen(
                 token = token,
                 email = currentState.email,
                 onChangeEmail = {
                     viewModel.updateUiState(state = UiState.Forgot)
                 },
                 goTo = {
                     viewModel.updateUiState(state = UiState.ChangePassword)
                 }
             )
        }

        UiState.ChangePassword -> {
            ChangePasswordScreen(
                token = token,
                goToLogin = {
                    viewModel.updateUiState(state = UiState.Login)
                }
            )
        }

        UiState.Onboarding -> {
            OnboardingMainScreen()
        }

        UiState.Home -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "HOME",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
