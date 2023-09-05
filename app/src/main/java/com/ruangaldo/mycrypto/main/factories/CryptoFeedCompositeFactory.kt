package com.ruangaldo.mycrypto.main.factories

import com.ruangaldo.mycrypto.crypto.feed.cache.usecases.LocalCryptoFeedLoader
import com.ruangaldo.mycrypto.crypto.feed.composite.usecase.CompositeCryptoFeedLoader
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedLoader

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedCompositeFactory {
    companion object {
        fun createCompositeFactory(
            primary: CryptoFeedLoader,
            fallback: CryptoFeedLoader
        ): CryptoFeedLoader {
            return CompositeCryptoFeedLoader(primary, fallback)
        }
    }
}
