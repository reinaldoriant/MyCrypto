package com.ruangaldo.mycrypto.crypto.feed.cache.usecases

import com.ruangaldo.mycrypto.crypto.feed.cache.CryptoFeedRoomClient
import com.ruangaldo.mycrypto.crypto.feed.cache.LocalClientResult
import com.ruangaldo.mycrypto.crypto.feed.cache.LocalDB
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedLoader
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedResult
import com.ruangaldo.mycrypto.crypto.feed.domain.LocalItemsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class LocalCryptoFeedLoader constructor(private val localDB: CryptoFeedRoomClient) : CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        localDB.get()
            .catch {
                emit(LocalClientResult.Failure(it))
            }
            .collect {
                when (it) {
                    is LocalClientResult.Success -> {
                        emit(
                            CryptoFeedResult.Success(
                                LocalItemsMapper.map(
                                    it.root
                                )
                            )
                        )
                    }

                    is LocalClientResult.Failure -> {
                        emit(CryptoFeedResult.Failure(it.throwable))
                    }
                }

            }
    }
}
