package com.ruangaldo.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ruangaldo.decorator.factories.CryptoFeedCompositeFactory
import com.ruangaldo.decorator.factories.CryptoFeedDecoratorFactory
import com.ruangaldo.decorator.factories.LocalCryptoFeedInsertFactory
import com.ruangaldo.decorator.factories.LocalCryptoFeedLoaderFactory
import com.ruangaldo.decorator.factories.RemoteCryptoFeedLoaderFactory
import com.ruangaldo.domain.CryptoFeedResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.ruangaldo.http.usecases.InvalidData

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

sealed interface CryptoFeedUiState {
    val isLoading: Boolean
    val failed: String

    data class HasCryptoFeed(
        override val isLoading: Boolean,
        val cryptoFeeds: List<com.ruangaldo.domain.CryptoFeedItem>,
        override val failed: String
    ) : CryptoFeedUiState

    data class NoCryptoFeed(
        override val isLoading: Boolean,
        override val failed: String,
    ) : CryptoFeedUiState
}

data class CryptoFeedViewModelState(
    val isLoading: Boolean = false,
    val cryptoFeeds: List<com.ruangaldo.domain.CryptoFeedItem> = emptyList(),
    val failed: String = ""
) {
    fun toCryptoFeedUiState(): CryptoFeedUiState =
        if (cryptoFeeds.isEmpty()) {
            CryptoFeedUiState.NoCryptoFeed(
                isLoading = isLoading,
                failed = failed
            )

        } else {
            CryptoFeedUiState.HasCryptoFeed(
                isLoading = isLoading,
                cryptoFeeds = cryptoFeeds,
                failed = failed
            )
        }
}

class CryptoFeedViewModel constructor(
    private val cryptoFeedLoader: com.ruangaldo.domain.CryptoFeedLoader
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        CryptoFeedViewModelState(
            isLoading = true,
            failed = ""
        )
    )

    val cryptoFeedUiState = viewModelState
        .map(CryptoFeedViewModelState::toCryptoFeedUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toCryptoFeedUiState()
        )

    init {
        loadCryptoFeed()
    }

    fun loadCryptoFeed() {
        viewModelScope.launch {
            cryptoFeedLoader.load().collect { result ->
                Log.d("loadCryptoFeed", "$result")
                viewModelState.update {
                    when (result) {
                        is CryptoFeedResult.Success -> it.copy(
                            cryptoFeeds = result.cryptoFeedItems,
                            isLoading = false
                        )

                        is CryptoFeedResult.Failure -> it.copy(
                            failed = when (result.throwable) {
                                is InvalidData -> "Invalid Data"
                                else -> "Something Went Wrong"
                            },
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    companion object {
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CryptoFeedViewModel(
                    CryptoFeedCompositeFactory.createCompositeFactory(
                        primary = CryptoFeedDecoratorFactory.create(
                            decorator = RemoteCryptoFeedLoaderFactory.create(),
                            cache = LocalCryptoFeedInsertFactory.create()
                        ),
                        fallback = LocalCryptoFeedLoaderFactory.create()
                    )
                )
            }
        }
    }
}
