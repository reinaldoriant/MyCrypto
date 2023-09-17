package com.ruangaldo.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

@Dao
interface CryptoFeedDAO {
    @Query("Select * From crypto_feed")
    fun load(): Flow<List<LocalRootCryptoFeed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<LocalRootCryptoFeed>)
}
