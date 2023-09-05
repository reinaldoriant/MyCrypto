package com.ruangaldo.mycrypto.main.factories

import com.ruangaldo.mycrypto.crypto.feed.cache.usecases.LocalCryptoFeedInsert
import com.ruangaldo.mycrypto.main.decorator.CryptoFeedCache

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
