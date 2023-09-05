package com.ruangaldo.mycrypto.frameworks

import android.app.Application
import androidx.room.Room
import com.ruangaldo.mycrypto.crypto.feed.cache.CryptoFeedDatabase

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

object LocalFactory {
    lateinit var application: Application

    fun createDatabase() = Room.databaseBuilder(
        application.applicationContext,
        CryptoFeedDatabase::class.java,
        "crypto"
    ).build()
}
