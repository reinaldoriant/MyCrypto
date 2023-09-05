package com.ruangaldo.mycrypto.crypto.detail.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ruangaldo.mycrypto.crypto.detail.ui.CryptoDetailsRoute
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedItem

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
        route = "$cryptoDetailsRoute/{$nameArg}",
        arguments = listOf(
            navArgument(nameArg) { type = NavType.StringType }
        )
    ) {
        CryptoDetailsRoute(
            popBackStack = popBackStack,
            name = it.arguments?.getString(nameArg)
        )
    }
}

