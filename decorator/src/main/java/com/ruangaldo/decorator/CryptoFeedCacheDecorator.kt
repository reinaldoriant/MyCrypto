package com.ruangaldo.decorator

import com.ruangaldo.cache.usecases.CryptoFeedCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedCacheDecorator(
    private val decorator: com.ruangaldo.domain.CryptoFeedLoader,
    private val cache: CryptoFeedCache
): com.ruangaldo.domain.CryptoFeedLoader {
    override fun load(): Flow<com.ruangaldo.domain.CryptoFeedResult> = flow{
        decorator.load().collect{result ->
            if (result is com.ruangaldo.domain.CryptoFeedResult.Success){
                cache.save(result.cryptoFeedItems)
            }
            emit(result)
        }
    }

}
