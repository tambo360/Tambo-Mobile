package com.tambo360.app.core.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

private val arLocale: Locale = Locale.forLanguageTag("es-AR")

fun formatNumber(value: Double): String {
    val formatter = NumberFormat.getNumberInstance(arLocale).apply {
        minimumFractionDigits = 0
        maximumFractionDigits = 2
    }
    return formatter.format(value)
}

fun formatCurrency(value: Double): String {
    val formatter = NumberFormat.getNumberInstance(arLocale).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return "$${formatter.format(value)}"
}

fun formatCompactCurrency(value: Double): String {
    return when {
        value >= 1_000_000 -> "$${formatNumber(value / 1_000_000)}M"
        value >= 1_000 -> "$${formatNumber(value / 1_000)}K"
        else -> "$${formatNumber(value)}"
    }
}

fun String.capitalizeFirst(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(arLocale) else it.toString() }

fun formatLoteNumber(value: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-AR")).apply {
        minimumFractionDigits = 0
        maximumFractionDigits = 2
    }
    return formatter.format(value)
}

fun formatLoteUnit(unidad: String): String {
    return when (unidad.trim().lowercase()) {
        "l", "lt", "lts", "litros" -> "L"
        "kg", "kgs", "kilogramos" -> "Kg"
        else -> unidad.replaceFirstChar { it.uppercase() }
    }
}

fun formatFullCurrency(amount: Number): String {
    val symbols = DecimalFormatSymbols(Locale("es", "AR")).apply {
        groupingSeparator = '.'
        decimalSeparator = ','
    }
    val formatter = DecimalFormat("$#,##0.##########", symbols)
    return formatter.format(amount)
}
