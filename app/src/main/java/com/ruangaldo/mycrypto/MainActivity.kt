package com.ruangaldo.mycrypto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ruangaldo.mycrypto.presentation.CryptoFeedUiState
import com.ruangaldo.mycrypto.presentation.CryptoFeedViewModel
import com.ruangaldo.mycrypto.ui.component.CryptoFeedList
import com.ruangaldo.mycrypto.ui.theme.MyCryptoTheme
import com.ruangaldo.mycrypto.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CryptoFeedRoute()
                }
            }
        }
    }
}

@Composable
fun CryptoFeedRoute(
    viewModel: CryptoFeedViewModel = viewModel(factory = CryptoFeedViewModel.FACTORY)
) {
    val cryptoFeedUiState by viewModel.cryptoFeedUiState.collectAsStateWithLifecycle()

    Log.d("loadCryptoFeed", "$cryptoFeedUiState")

    CryptoFeedScreen(
        cryptoFeedUiState = cryptoFeedUiState
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CryptoFeedScreen(
    modifier: Modifier = Modifier,
    cryptoFeedUiState: CryptoFeedUiState,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = cryptoFeedUiState.isLoading,
        onRefresh = {}
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
                containerColor = Purple40
            )
        )
    }, content = {
        val contentModifier = modifier
            .padding(it)
            .fillMaxSize()

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
