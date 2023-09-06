package com.ruangaldo.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ruangaldo.shared.cryptoFeedRoute
import com.ruangaldo.shared.cryptoGraphRoute

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

fun NavGraphBuilder.cryptoGraph(
    onCryptoClick: (com.ruangaldo.domain.CryptoFeedItem) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = cryptoGraphRoute,
        startDestination = cryptoFeedRoute
    ) {
        composable(
            route = cryptoFeedRoute
        ) {
            CryptoFeedRoute(onNavigateToCryptoDetails = onCryptoClick)
        }
        nestedGraphs()
    }
}
