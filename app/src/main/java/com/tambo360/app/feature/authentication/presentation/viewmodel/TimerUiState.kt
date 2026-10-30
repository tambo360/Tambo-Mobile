package com.tambo360.app.feature.authentication.presentation.viewmodel

data class TimerUiState(
    val totalTime: Long = 0L,
    val remainingTime: Long = 0L,
    val isRunning: Boolean = false,
    val isFinished: Boolean = false,
    val formattedTime: String = "00:00"
)
