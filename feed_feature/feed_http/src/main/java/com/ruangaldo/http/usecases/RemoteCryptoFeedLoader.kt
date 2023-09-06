package com.ruangaldo.http.usecases

import com.ruangaldo.http.ConnectivityException
import com.ruangaldo.http.CryptoFeedHttpClient
import com.ruangaldo.http.CryptoFeedItemsMapper
import com.ruangaldo.http.HttpClientResult
import com.ruangaldo.http.InvalidDataException
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class InvalidData : Throwable()
class Connectivity : Throwable()
class RemoteCryptoFeedLoader constructor(private val cryptoFeedHttpClient: CryptoFeedHttpClient) :
    com.ruangaldo.domain.CryptoFeedLoader {
    override fun load(): Flow<com.ruangaldo.domain.CryptoFeedResult> = flow {
        cryptoFeedHttpClient.get().collect { result ->
            when (result) {
                is HttpClientResult.Success -> {
                    val cryptoFeed = result.root.data
                    if (cryptoFeed.isNotEmpty()) {
                        emit(com.ruangaldo.domain.CryptoFeedResult.Success(CryptoFeedItemsMapper.map(cryptoFeed)))
                    } else {
                        emit(com.ruangaldo.domain.CryptoFeedResult.Success(emptyList()))
                    }
                }

                is HttpClientResult.Failure -> {
                    Log.d("loadCryptoFeed", "Failure")
                    when (result.throwable) {
                        is ConnectivityException -> {
                            emit(com.ruangaldo.domain.CryptoFeedResult.Failure(Connectivity()))
                        }

                        is InvalidDataException -> {
                            Log.d("loadCryptoFeed", "InvalidData")
                            emit(com.ruangaldo.domain.CryptoFeedResult.Failure(InvalidData()))
                        }
                    }
                }
            }
        }
    }
}
