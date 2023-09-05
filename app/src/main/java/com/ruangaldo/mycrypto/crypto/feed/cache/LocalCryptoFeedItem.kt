package com.ruangaldo.mycrypto.crypto.feed.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

@Entity(tableName = "crypto_feed")
data class LocalRootCryptoFeed(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "full_name")
    val fullName: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "price")
    val price: Double?,
    @ColumnInfo(name = "changepctday")
    val changePctDay: Float
)
