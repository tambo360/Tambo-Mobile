package com.tambo360.app.feature.authentication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tambo360.app.feature.authentication.presentation.content.RegisterContent
import com.tambo360.app.feature.authentication.presentation.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    goTo: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var registeredEmail by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        uiState.data?.let {
            if (it.success) {
                goTo(registeredEmail)
            }
        }
    }

    RegisterContent(
        state = uiState,
        onRegisterClick = { name, email, pass ->
            registeredEmail = email
            viewModel.registerUser(
                name = name,
                email = email,
                password = pass
            )
        }
    )
}
