package com.ruangaldo.cache.usecases

import com.ruangaldo.cache.CryptoFeedRoomClient
import com.ruangaldo.domain.CryptoFeedItem

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

interface CryptoFeedCache{
    suspend fun save(list: List<CryptoFeedItem>)
}

class LocalCryptoFeedInsert constructor(
    private val cryptoFeedLocalClient: CryptoFeedRoomClient
): CryptoFeedCache {
    override suspend fun save(list: List<com.ruangaldo.domain.CryptoFeedItem>) {
        val data = LocalFeedItemsMapper.map(list)
        cryptoFeedLocalClient.insert(
            data
        )
    }
}
