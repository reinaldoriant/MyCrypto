package com.ruangaldo.mycrypto.main.decorator

import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedItem
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedLoader
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

interface CryptoFeedCache{
    suspend fun save(list: List<CryptoFeedItem>)
}
class CryptoFeedCacheDecorator(
    private val decorator: CryptoFeedLoader,
    private val cache: CryptoFeedCache
): CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow{
        decorator.load().collect{result ->
            if (result is CryptoFeedResult.Success){
                cache.save(result.cryptoFeedItems)
            }
            emit(result)
        }
    }

}
