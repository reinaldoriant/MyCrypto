package com.ruangaldo.decorator.factories

import com.ruangaldo.cache.usecases.CryptoFeedCache
import com.ruangaldo.decorator.CryptoFeedCacheDecorator
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
