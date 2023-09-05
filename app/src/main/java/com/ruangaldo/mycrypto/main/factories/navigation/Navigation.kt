package com.ruangaldo.mycrypto.main.factories.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedItem
import com.ruangaldo.mycrypto.crypto.feed.ui.CryptoFeedRoute

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

const val cryptoGraphRoute = "crypto_graph_route"
const val cryptoFeedRoute = "crypto_feed_route"

fun NavGraphBuilder.cryptoGraph(
    onCryptoClick: (CryptoFeedItem) -> Unit,
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
