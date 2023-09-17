package com.ruangaldo.mycrypto.navigation.feed

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ruangaldo.mycrypto.factories.ViewModelFactory
import com.ruangaldo.presentation.CryptoFeedViewModel
import com.ruangaldo.ui.CryptoFeedScreen

/**
 * Written with joy and smile by Ruang Aldo on 06/09/23.
 * Github: https://github.com/reinaldoriant
 */

@Composable
fun CryptoFeedRoute(
    viewModel: CryptoFeedViewModel = viewModel(factory = ViewModelFactory.FACTORY),
    onNavigateToCryptoDetails: (com.ruangaldo.domain.CryptoFeedItem) -> Unit
) {
    val cryptoFeedUiState by viewModel.cryptoFeedUiState.collectAsStateWithLifecycle()

    Log.d("loadCryptoFeed", "$cryptoFeedUiState")

    CryptoFeedScreen(
        cryptoFeedUiState = cryptoFeedUiState,
        onRefreshCryptoFeed = viewModel::loadCryptoFeed,
        onNavigateToCryptoDetails = onNavigateToCryptoDetails
    )
}
