package com.ruangaldo.decorator.factories

import com.ruangaldo.cache.usecases.LocalCryptoFeedLoader
import com.ruangaldo.domain.CryptoFeedLoader

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class LocalCryptoFeedLoaderFactory {
    companion object {
        fun create(): CryptoFeedLoader {
            return LocalCryptoFeedLoader(CreateFeedDaoFactory.createCryptoFeedDao())
        }
    }
}
