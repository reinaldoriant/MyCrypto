package com.ruangaldo.cache.framework

import android.app.Application
import androidx.room.Room

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

object LocalFactory {
    lateinit var application: Application

    fun createDatabase() = Room.databaseBuilder(
        application.applicationContext,
        com.ruangaldo.cache.CryptoFeedDatabase::class.java,
        "crypto"
    ).build()
}
