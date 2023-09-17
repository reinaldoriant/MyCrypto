package com.ruangaldo.mycrypto.factories

import com.ruangaldo.cache.usecases.CryptoFeedCache
import com.ruangaldo.mycrypto.decorator.CryptoFeedCacheDecorator
import com.ruangaldo.domain.CryptoFeedLoader

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedDecoratorFactory {
    companion object{
        fun create(
            decorator: CryptoFeedLoader,
            cache: CryptoFeedCache
        ): CryptoFeedLoader {
            return CryptoFeedCacheDecorator(decorator, cache)
        }
    }
}
