package com.ruangaldo.mycrypto.crypto.feed.domain

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

data class CryptoFeedItem(
    val coinInfo: CoinInfoItem,
    val raw: RawItem,
)

data class CoinInfoItem(
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String
)

data class RawItem(
    val usd: UsdItem
)

data class UsdItem(
    val price: Double,
    val changePctDay: Float
)
