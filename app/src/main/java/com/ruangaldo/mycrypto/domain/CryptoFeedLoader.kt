package com.ruangaldo.mycrypto.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ruangaldo.mycrypto.factories.RemoteCryptoFeedLoaderFactory
import com.ruangaldo.mycrypto.http.usecases.Connectivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

sealed class CryptoFeedResult {
    data class Success(val cryptoFeedItems: List<CryptoFeedItem>) : CryptoFeedResult()
    data class Failure(val throwable: Throwable) : CryptoFeedResult()
}

sealed interface CryptoFeedUiState {
    val isLoading: Boolean
    val failed: String

    data class NoCryptoFeed(
        override val isLoading: Boolean,
        override val failed: String,
    ) : CryptoFeedUiState
}

data class CryptoFeedViewModelState(
    val isLoading: Boolean = false,
    val cryptoFeeds: List<CryptoFeedItem>? = null,
    val failed: String = ""
) {
    fun toCryptoFeedUiState(): CryptoFeedUiState =
        CryptoFeedUiState.NoCryptoFeed(
            isLoading = isLoading,
            failed = failed
        )
}

interface CryptoFeedLoader {
    fun load(): Flow<CryptoFeedResult>
}

class CryptoFeedViewModel constructor(
    private val cryptoFeedLoader: CryptoFeedLoader
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

    private fun loadCryptoFeed() {
        viewModelScope.launch {
            cryptoFeedLoader.load().collect { result ->
                if (result is CryptoFeedResult.Failure) {
                    viewModelState.update {
                        it.copy(
                            failed = if(result.throwable is Connectivity) {
                                "Connectivity"
                            } else {
                                "Something Went Wrong"
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
                    RemoteCryptoFeedLoaderFactory.createRemoteCryptoFeedLoader()
                )
            }
        }
    }
}
