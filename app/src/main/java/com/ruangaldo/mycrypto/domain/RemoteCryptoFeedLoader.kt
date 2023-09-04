package com.ruangaldo.mycrypto.domain

import com.ruangaldo.mycrypto.http.CryptoFeedHttpClient
import com.ruangaldo.mycrypto.http.HttpClientResult
import com.ruangaldo.mycrypto.http.usecases.CryptoFeedLoader
import com.ruangaldo.mycrypto.http.usecases.CryptoFeedResult
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
            if (result is HttpClientResult.Success) {
                val cryptoFeed = result.root.data
                if (!cryptoFeed.isNullOrEmpty()) {
                    emit(CryptoFeedResult.Success(CryptoFeedItemsMapper.map(cryptoFeed)))
                } else {
                    emit(CryptoFeedResult.Success(emptyList()))
                }
            }

            if (result is HttpClientResult.Failure) {
                emit(CryptoFeedResult.Failure(InvalidData()))
            }

            if (result is HttpClientResult.Failure) {
                emit(CryptoFeedResult.Failure(Connectivity()))
            }
        }
    }
}

class InvalidData : Throwable()
class Connectivity : Throwable()
