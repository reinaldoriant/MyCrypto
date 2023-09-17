package com.ruangaldo.cache

import kotlinx.coroutines.flow.Flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

sealed class LocalClientResult {
    data class Success(val root: List<LocalRootCryptoFeed>) : LocalClientResult()
    data class Failure(val throwable: Throwable) : LocalClientResult()
}

interface LocalDB{
    fun get(): Flow<LocalClientResult>
    suspend fun insert(data: List<LocalRootCryptoFeed>)
}
