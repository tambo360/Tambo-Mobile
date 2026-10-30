package com.tambo360.app.feature.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.authentication.data.dto.VerifyEmailRequest
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class VerifyEmailViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerifyEmailUiState())
    val uiState = _uiState.asStateFlow()

    private val _timerState = MutableStateFlow(TimerUiState())
    val timerState: StateFlow<TimerUiState> = _timerState.asStateFlow()

    @Volatile
    private var timerIsRunning = false
    private val timerUpdateIntervalValue = 1000L

    fun verifyEmail(token: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            val request = VerifyEmailRequest(
                token = token
            )

            when (val result = repository.verifyEmail(request)) {
                is NetworkResult.Success -> {
                    _uiState.update { it.copy(loading = false, data = result.data) }
                }

                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(loading = false, error = cleanMessage) }
                }
            }
        }
    }

    fun configureTimer(timeInSeconds: Long) {
        val timeInMillis = timeInSeconds * 1000L
        _timerState.update {
            it.copy(
                totalTime = timeInMillis,
                remainingTime = timeInMillis,
                isFinished = false,
                formattedTime = formatTime(timeInMillis)
            )
        }
    }

    fun startTimer() {
        if (timerIsRunning || _timerState.value.remainingTime <= 0) return

        timerIsRunning = true
        _timerState.update { it.copy(isRunning = true) }

        viewModelScope.launch(Dispatchers.Main) {
            while (timerIsRunning) {
                delay(timerUpdateIntervalValue.milliseconds)

                val currentRemaining = _timerState.value.remainingTime - timerUpdateIntervalValue

                if (currentRemaining <= 0L) {
                    stopTimer(isFinished = true)
                } else {
                    _timerState.update {
                        it.copy(
                            remainingTime = currentRemaining,
                            formattedTime = formatTime(currentRemaining)
                        )
                    }
                }
            }
        }
    }

    fun pauseTimer() {
        if (timerIsRunning) {
            timerIsRunning = false
            _timerState.update { it.copy(isRunning = false) }
        }
    }

    fun stopTimer(isFinished: Boolean = false) {
        timerIsRunning = false
        _timerState.update {
            it.copy(
                isRunning = false,
                isFinished = isFinished,
                remainingTime = if (isFinished) 0L else it.remainingTime,
                formattedTime = if (isFinished) "00:00" else it.formattedTime
            )
        }
    }

    private fun formatTime(timeInMillis: Long): String {
        val totalSeconds = timeInMillis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }
}
