package com.ruangaldo.http

import com.squareup.moshi.Json

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

data class RemoteRootCryptoFeed(
    @Json(name = "Data")
    val data: List<RemoteCryptoFeedItem>
)

data class RemoteCryptoFeedItem(
    @Json(name = "CoinInfo")
    val remoteCoinInfo: RemoteCoinInfo,
    @Json(name = "RAW")
    val remoteRaw: RemoteDisplay
)

data class RemoteCoinInfo(
    @Json(name = "Id")
    val id: String?,
    @Json(name = "Name")
    val name: String?,
    @Json(name = "FullName")
    val fullName: String?,
    @Json(name = "ImageUrl")
    val imageUrl: String?,
)

data class RemoteDisplay(
    @Json(name = "USD")
    val usd: RemoteUsd
)

data class RemoteUsd(
    @Json(name = "PRICE")
    val price: Double?,
    @Json(name = "CHANGEPCTDAY")
    val changePctDay: Float?
)
