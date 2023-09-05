package com.ruangaldo.mycrypto.crypto.feed.cache

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

@Database(
    entities = [LocalRootCryptoFeed::class],
    version = 1,
    exportSchema = false
)
abstract class CryptoFeedDatabase: RoomDatabase() {
    abstract fun cryptoFeedDao(): CryptoFeedDAO
}
