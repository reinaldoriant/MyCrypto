package com.ruangaldo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ruangaldo.presentation.CryptoFeedUiState
import com.ruangaldo.ui.component.CryptoFeedList

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CryptoFeedScreen(
    modifier: Modifier = Modifier,
    cryptoFeedUiState: CryptoFeedUiState,
    onRefreshCryptoFeed: () -> Unit,
    onNavigateToCryptoDetails: (com.ruangaldo.domain.CryptoFeedItem) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = cryptoFeedUiState.isLoading,
        onRefresh = onRefreshCryptoFeed
    )

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Crypto Feed",
                    maxLines = 1,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = com.ruangaldo.shared.theme.Purple40
            )
        )
    }, content = {
        val contentModifier = modifier
            .padding(it)
            .fillMaxSize()
            .pullRefresh(pullRefreshState)

        LoadingContent(
            pullRefreshState = pullRefreshState,
            loading = cryptoFeedUiState.isLoading,
            empty = when (cryptoFeedUiState) {
                is CryptoFeedUiState.HasCryptoFeed -> false
                is CryptoFeedUiState.NoCryptoFeed -> cryptoFeedUiState.isLoading
            },
            emptyContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    PullRefresh(
                        loading = cryptoFeedUiState.isLoading,
                        pullRefreshState = pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            },
            content = {
                when (cryptoFeedUiState) {
                    is CryptoFeedUiState.HasCryptoFeed -> {
                        CryptoFeedList(
                            contentModifier = contentModifier,
                            items = cryptoFeedUiState.cryptoFeeds,
                            onNavigateToCryptoDetails = onNavigateToCryptoDetails
                        )
                    }

                    is CryptoFeedUiState.NoCryptoFeed -> {
                        if (cryptoFeedUiState.failed.isEmpty()) {
                            Box(
                                modifier = modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                Text(
                                    "Crypto Feed Empty",
                                )
                            }
                        }
                    }
                }
            })

        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                cryptoFeedUiState.failed,
            )
        }
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadingContent(
    loading: Boolean,
    pullRefreshState: PullRefreshState,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    if (empty) {
        emptyContent()
    } else {
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            content()

            PullRefresh(
                loading = loading,
                pullRefreshState = pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefresh(
    loading: Boolean,
    pullRefreshState: PullRefreshState,
    modifier: Modifier
) {
    PullRefreshIndicator(
        refreshing = loading,
        state = pullRefreshState,
        modifier = modifier
    )
}
