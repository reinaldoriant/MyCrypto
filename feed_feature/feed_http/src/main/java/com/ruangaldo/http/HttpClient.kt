package com.ruangaldo.http

import kotlinx.coroutines.flow.Flow

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

sealed class HttpClientResult {
    data class Success(val root: RemoteRootCryptoFeed) : HttpClientResult()
    data class Failure(val throwable: Throwable) : HttpClientResult()
}

class InvalidDataException : Throwable()
class ConnectivityException : Throwable()

interface CryptoFeedHttpClient {
    fun get(): Flow<HttpClientResult>
}

