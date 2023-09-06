package com.ruangaldo.decorator.factories

import com.ruangaldo.cache.usecases.CryptoFeedCache
import com.ruangaldo.cache.usecases.LocalCryptoFeedInsert

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class LocalCryptoFeedInsertFactory {
    companion object {
        fun create(): CryptoFeedCache =
            LocalCryptoFeedInsert(CreateFeedDaoFactory.createCryptoFeedDao())
    }
}
