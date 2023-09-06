package com.ruangaldo.mycrypto.factories

import com.ruangaldo.cache.framework.LocalFactory

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CreateFeedDaoFactory {
    companion object{
        fun createCryptoFeedDao(): com.ruangaldo.cache.CryptoFeedRoomClient {
            return com.ruangaldo.cache.CryptoFeedRoomClient(
                LocalFactory.createDatabase().cryptoFeedDao()
            )
        }
    }
}
