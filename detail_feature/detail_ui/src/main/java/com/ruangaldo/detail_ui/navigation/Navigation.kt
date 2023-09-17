package com.ruangaldo.detail_ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ruangaldo.detail_ui.CryptoDetailsRoute
import com.ruangaldo.domain.CryptoFeedItem

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

internal const val nameArg = "name"
const val cryptoDetailsRoute = "crypto_details_route/{$nameArg}"

fun NavController.navigateToCryptoDetails(name: CryptoFeedItem, navOptions: NavOptions? = null) {
    this.navigate("$cryptoDetailsRoute/${name.coinInfo.name}", navOptions)
}

fun NavGraphBuilder.cryptoDetailScreen(
    popBackStack: () -> Unit,
) {
    composable(
        route = "$cryptoDetailsRoute/{${com.ruangaldo.detail_ui.navigation.nameArg}}",
        arguments = listOf(
            navArgument(com.ruangaldo.detail_ui.navigation.nameArg) { type = NavType.StringType }
        )
    ) {
        CryptoDetailsRoute(
            popBackStack = popBackStack,
            name = it.arguments?.getString(com.ruangaldo.detail_ui.navigation.nameArg)
        )
    }
}

