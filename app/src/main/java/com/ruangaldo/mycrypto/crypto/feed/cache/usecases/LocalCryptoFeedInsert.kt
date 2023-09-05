package com.ruangaldo.mycrypto.crypto.feed.cache.usecases

import com.ruangaldo.mycrypto.crypto.feed.cache.CryptoFeedRoomClient
import com.ruangaldo.mycrypto.crypto.feed.cache.LocalRootCryptoFeed
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedItem
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedItemsMapper
import com.ruangaldo.mycrypto.main.decorator.CryptoFeedCache

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class LocalCryptoFeedInsert constructor(
    private val cryptoFeedLocalClient: CryptoFeedRoomClient
): CryptoFeedCache {
    override suspend fun save(list: List<CryptoFeedItem>) {
        val data = CryptoFeedItemsMapper.mapLocal(list)
        cryptoFeedLocalClient.insert(
            data
        )
    }
}
