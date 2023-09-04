package com.ruangaldo.mycrypto

import com.ruangaldo.mycrypto.domain.Connectivity
import com.ruangaldo.mycrypto.domain.InvalidData
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

class CryptoFeedRetrofitHttpClient: HttpClient {
    override fun get(): Flow<HttpClientResult> = flow {
        try { } catch (throwable: Throwable) {
            if (throwable is IOException) {
                emit(HttpClientResult.Failure(Connectivity()))
            }

            if (throwable is HttpException) {
                if (throwable.code() == 422) {
                    HttpClientResult.Failure(InvalidData())
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}
