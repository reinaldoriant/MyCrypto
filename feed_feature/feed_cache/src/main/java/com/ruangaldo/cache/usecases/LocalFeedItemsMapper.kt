package com.ruangaldo.cache.usecases

import com.ruangaldo.domain.CryptoFeedItem

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class LocalFeedItemsMapper {
    companion object {
        fun map(items: List<CryptoFeedItem>): List<com.ruangaldo.cache.LocalRootCryptoFeed> = items.map {
            com.ruangaldo.cache.LocalRootCryptoFeed(
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
