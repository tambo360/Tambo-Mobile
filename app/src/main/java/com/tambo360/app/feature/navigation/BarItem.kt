package com.tambo360.app.feature.navigation

import com.tambo360.app.R

sealed class BarItem(
    val screen: Any,
    val title: String,
    val icon: Int
) {
    data object Home : BarItem(
        screen = HomeRoute,
        title = "Inicio",
        icon = R.drawable.ic_home
    )

    data object Production : BarItem(
        screen = ProductionRoute,
        title = "Producción",
        icon = R.drawable.ic_production
    )

    data object Costs : BarItem(
        screen = CostsRoute,
        title = "Costos",
        icon = R.drawable.ic_costs
    )

    data object Ai : BarItem(
        screen = AiRoute,
        title = "IA",
        icon = R.drawable.ic_ia
    )

    data object Settings : BarItem(
        screen = SettingsRoute,
        title = "Ajustes",
        icon = R.drawable.ic_config
    )

    companion object {
        val items = listOf(Home, Production, Costs, Ai, Settings)
    }
}
