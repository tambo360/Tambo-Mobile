package com.tambo360.app.feature.onboarding.presentation.util

import com.tambo360.app.feature.onboarding.data.dto.request.RazaRequest

object BreedManagerUtil {
    fun processAndAddBreed(
        breedName: String,
        countString: String,
        targetList: MutableList<RazaRequest>
    ) {
        val count = countString.toIntOrDefault()
        if (count > 0) {
            targetList.add(RazaRequest(breedName, count))
        }
    }
}

fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun String?.orEmptyString(): String = this ?: ""

fun String.toDoubleOrDefault(): Double = this.toDoubleOrNull().orZero()
fun String.toIntOrDefault(): Int = this.toIntOrNull().orZero()
