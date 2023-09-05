package com.ruangaldo.mycrypto.main.factories

import com.ruangaldo.mycrypto.crypto.feed.cache.CryptoFeedRoomClient
import com.ruangaldo.mycrypto.frameworks.LocalFactory

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CreateFeedDaoFactory {
    companion object{
        fun createCryptoFeedDao(): CryptoFeedRoomClient{
            return CryptoFeedRoomClient(LocalFactory.createDatabase().cryptoFeedDao())
        }
    }
}
