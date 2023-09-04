package com.ruangaldo.mycrypto.http.usecases

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.ruangaldo.mycrypto.domain.CryptoFeedItemsMapper
import com.ruangaldo.mycrypto.domain.CryptoFeedLoader
import com.ruangaldo.mycrypto.domain.CryptoFeedResult
import com.ruangaldo.mycrypto.http.ConnectivityException
import com.ruangaldo.mycrypto.http.CryptoFeedHttpClient
import com.ruangaldo.mycrypto.http.HttpClientResult
import com.ruangaldo.mycrypto.http.InvalidDataException
import com.ruangaldo.mycrypto.presentation.CryptoFeedUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class RemoteCryptoFeedLoader constructor(private val cryptoFeedHttpClient: CryptoFeedHttpClient) :
    CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        cryptoFeedHttpClient.get().collect { result ->
            when (result) {
                is HttpClientResult.Success -> {
                    val cryptoFeed = result.root.data
                    if (cryptoFeed.isNotEmpty()) {
                        emit(CryptoFeedResult.Success(CryptoFeedItemsMapper.map(cryptoFeed)))
                    } else {
                        emit(CryptoFeedResult.Success(emptyList()))
                    }
                }

                is HttpClientResult.Failure -> {
                    Log.d("loadCryptoFeed", "Failure")
                    when (result.throwable) {
                        is ConnectivityException -> {
                            emit(CryptoFeedResult.Failure(Connectivity()))
                        }

                        is InvalidDataException -> {
                            Log.d("loadCryptoFeed", "InvalidData")
                            emit(CryptoFeedResult.Failure(InvalidData()))
                        }
                    }
                }
            }
        }
    }
}
class InvalidData : Throwable()
class Connectivity : Throwable()
