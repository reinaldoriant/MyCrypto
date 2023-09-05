package com.ruangaldo.mycrypto.crypto.feed.domain

import com.ruangaldo.mycrypto.crypto.feed.cache.LocalRootCryptoFeed
import com.ruangaldo.mycrypto.crypto.feed.http.RemoteCryptoFeedItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedItemsMapper {
    companion object {
        fun map(items: List<RemoteCryptoFeedItem>): List<CryptoFeedItem> {
            return items.map {
                CryptoFeedItem(
                    coinInfo = CoinInfoItem(
                        it.remoteCoinInfo.id.orEmpty(),
                        it.remoteCoinInfo.name.orEmpty(),
                        it.remoteCoinInfo.fullName.orEmpty(),
                        it.remoteCoinInfo.imageUrl.orEmpty()
                    ),
                    raw = RawItem(
                        usd = UsdItem(
                            it.remoteRaw.usd.price ?: 0.0,
                            it.remoteRaw.usd.changePctDay ?: 0F
                        )
                    )
                )
            }
        }
        fun mapLocal(items: List<CryptoFeedItem>): List<LocalRootCryptoFeed> = items.map {
            LocalRootCryptoFeed(
                id = it.coinInfo.id,
                name = it.coinInfo.name,
                fullName = it.coinInfo.fullName,
                imageUrl = it.coinInfo.imageUrl,
                price = it.raw.usd.price,
                changePctDay = it.raw.usd.changePctDay
            )
        }
    }
}
