package com.tambo360.app.feature.authentication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tambo360.app.feature.authentication.presentation.content.ForgotPasswordContent
import com.tambo360.app.feature.authentication.presentation.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    goBack : () -> Unit,
    goTo: (String) -> Unit,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var forgotEmail by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        state.data?.let {
            if (it.success) {
                goTo(forgotEmail)
                viewModel.resetState()
            }
        }
    }
    ForgotPasswordContent(
        state = state,
        goBack = goBack,
        sendEmail = { email ->
            forgotEmail = email
            viewModel.forgotPassword(email = email)
        }
    )
}
