package com.tambo360.app.feature.authentication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tambo360.app.core.ui.components.LoadingScreen
import com.tambo360.app.feature.authentication.presentation.content.CheckEmailContent
import com.tambo360.app.feature.authentication.presentation.content.VerificationContent
import com.tambo360.app.feature.authentication.presentation.viewmodel.VerifyEmailViewModel

@Composable
fun CheckEmailScreen(
    email: String,
    token: String?,
    onChangeEmail: () -> Unit,
    goToOnboarding : () -> Unit,
    viewModel: VerifyEmailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val timerState by viewModel.timerState.collectAsStateWithLifecycle()
    LaunchedEffect(token) {
        if (!token.isNullOrEmpty()) {
            viewModel.verifyEmail(token)
        }
    }

    when {
        state.loading -> {
            LoadingScreen()
        }
        state.data?.success == true -> {
            VerificationContent(goToOnboarding = goToOnboarding)
        }
        else -> {
            CheckEmailContent(
                state = state,
                email = email,
                timerState = timerState,
                onChangeEmail = onChangeEmail,
                onResendClick = {
                    viewModel.resendVerification(email = email)
                }
            )
        }
    }
}
