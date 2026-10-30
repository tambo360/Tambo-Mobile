package com.tambo360.app.feature.authentication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tambo360.app.feature.authentication.presentation.content.CheckEmailContent
import com.tambo360.app.feature.authentication.presentation.viewmodel.VerifyEmailViewModel

@Composable
fun CheckEmailScreen(
    token: String?,
    onVerificationSuccess: () -> Unit,
    viewModel: VerifyEmailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val timerState by viewModel.timerState.collectAsStateWithLifecycle()
    LaunchedEffect(token) {
        if (!token.isNullOrEmpty()) {
            viewModel.verifyEmail(token)
        }
    }
    LaunchedEffect(state.data) {
        if (state.data?.success == true) {
            onVerificationSuccess()
        }
    }

    CheckEmailContent(
        state = state,
        timerState = timerState,
        onResendClick = {
            viewModel.configureTimer(180L)
            viewModel.startTimer()
        }
    )
}
