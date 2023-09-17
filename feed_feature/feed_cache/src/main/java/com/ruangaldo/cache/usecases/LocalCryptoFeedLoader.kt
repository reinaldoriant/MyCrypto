package com.ruangaldo.cache.usecases

import com.ruangaldo.cache.CryptoFeedRoomClient
import com.ruangaldo.cache.LocalClientResult
import com.ruangaldo.cache.LocalItemsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class LocalCryptoFeedLoader constructor(private val localDB: CryptoFeedRoomClient) :
    com.ruangaldo.domain.CryptoFeedLoader {
    override fun load(): Flow<com.ruangaldo.domain.CryptoFeedResult> = flow {
        localDB.get()
            .catch {
                emit(LocalClientResult.Failure(it))
            }
            .collect {
                when (it) {
                    is LocalClientResult.Success -> {
                        emit(
                            com.ruangaldo.domain.CryptoFeedResult.Success(
                                LocalItemsMapper.map(
                                    it.root
                                )
                            )
                        )
                    }

                    is LocalClientResult.Failure -> {
                        emit(com.ruangaldo.domain.CryptoFeedResult.Failure(it.throwable))
                    }
                }

            }
    }
}
