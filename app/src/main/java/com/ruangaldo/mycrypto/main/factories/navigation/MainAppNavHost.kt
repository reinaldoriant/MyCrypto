package com.ruangaldo.mycrypto.main.factories.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ruangaldo.mycrypto.crypto.detail.ui.navigation.cryptoDetailScreen
import com.ruangaldo.mycrypto.crypto.detail.ui.navigation.navigateToCryptoDetails
import com.ruangaldo.mycrypto.crypto.feed.ui.navigation.cryptoGraph
import com.ruangaldo.mycrypto.crypto.feed.ui.navigation.cryptoGraphRoute

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = cryptoGraphRoute
) {
    NavHost(
        navController = navHostController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        cryptoGraph(
            onCryptoClick = navHostController::navigateToCryptoDetails
        ) {
            cryptoDetailScreen(
                popBackStack = navHostController::popBackStack
            )
        }
    }
}
