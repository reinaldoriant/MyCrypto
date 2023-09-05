package com.ruangaldo.mycrypto.crypto.feed.http

import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedRetrofitHttpClient constructor(private val cryptoFeedService: CryptoFeedService) :
    CryptoFeedHttpClient {
    override fun get(): Flow<HttpClientResult> = flow {
        try {
            emit(HttpClientResult.Success(cryptoFeedService.get()))
        } catch (throwable: Throwable) {
            if (throwable is IOException) {
                emit(HttpClientResult.Failure(ConnectivityException()))
            }

            if (throwable is HttpException) {
                if (throwable.code() == 422) {
                    emit(HttpClientResult.Failure(InvalidDataException()))
                }
            }
            emit(HttpClientResult.Failure(InvalidDataException()))
        }
    }.flowOn(Dispatchers.IO)
}
