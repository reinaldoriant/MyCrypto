package com.ruangaldo.mycrypto.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ruangaldo.detail_ui.navigation.cryptoDetailScreen
import com.ruangaldo.detail_ui.navigation.navigateToCryptoDetails
import com.ruangaldo.shared.cryptoGraphRoute

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
