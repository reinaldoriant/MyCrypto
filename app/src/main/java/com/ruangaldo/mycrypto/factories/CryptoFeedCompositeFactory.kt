package com.ruangaldo.mycrypto.factories

import com.ruangaldo.mycrypto.composite.CompositeCryptoFeedLoader
import com.ruangaldo.domain.CryptoFeedLoader

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
