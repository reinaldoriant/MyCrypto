package com.ruangaldo.mycrypto.main.factories

import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedLoader
import com.ruangaldo.mycrypto.main.decorator.CryptoFeedCache
import com.ruangaldo.mycrypto.main.decorator.CryptoFeedCacheDecorator

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
