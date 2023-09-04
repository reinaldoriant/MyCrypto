package com.ruangaldo.mycrypto.http

import kotlinx.coroutines.flow.Flow

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

sealed class HttpClientResult {
    data class Success(val data: List<RemoteCryptoFeedItem>) : HttpClientResult()
    data class Failure(val throwable: Throwable) : HttpClientResult()
}

interface HttpClient {
    fun get(): Flow<HttpClientResult>
}
