package com.ruangaldo.mycrypto.crypto.feed.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedRoomClient constructor(
    private val cryptoDao: CryptoFeedDAO
): LocalDB {
    override fun get(): Flow<LocalClientResult> = flow {
        cryptoDao.load()
            .catch {
                emit(LocalClientResult.Failure(it))
            }.collect{
                emit(LocalClientResult.Success(it))
            }
    }
    override suspend fun insert(data: List<LocalRootCryptoFeed>) {
        cryptoDao.insert(data)
    }
}
