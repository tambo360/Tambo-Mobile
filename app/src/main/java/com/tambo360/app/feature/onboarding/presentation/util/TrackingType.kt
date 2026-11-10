package com.tambo360.app.feature.onboarding.presentation.util

sealed class TrackingType(val apiValue: String) {
    object Herd : TrackingType("RODEO")
    object SingleHerd : TrackingType("RODEO_UNICO")
    object Individual : TrackingType("INDIVIDUAL")
}
