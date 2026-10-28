package com.tambo360.app.feature.authentication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tambo360.app.feature.authentication.presentation.content.LoginContent
import com.tambo360.app.feature.authentication.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    goToRecoverPassword: () -> Unit,
    goToRegister: () -> Unit,
    goTo: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state) {
        state.data?.let {
            if (it.success) {
                goTo()
            }
        }
    }

    LoginContent(
        state = state,
        goToRecoverPassword = goToRecoverPassword,
        goToRegister = goToRegister,
        onLoginClick = { email, pass ->
            viewModel.loginUser(email = email, password = pass)
        }
    )
}
