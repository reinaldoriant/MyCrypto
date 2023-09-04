package com.ruangaldo.mycrypto.domain

import com.ruangaldo.mycrypto.CryptoFeedRetrofitHttpClient
import com.ruangaldo.mycrypto.HttpClientResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class RemoteCryptoFeedLoader constructor(private val httpClient: CryptoFeedRetrofitHttpClient) {
    fun load(): Flow<CryptoFeedResult> = flow {
        httpClient.get().collect { result ->
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
