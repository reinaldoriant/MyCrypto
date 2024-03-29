package com.ruangaldo.cache

import com.ruangaldo.domain.CoinInfoItem
import com.ruangaldo.domain.CryptoFeedItem
import com.ruangaldo.domain.RawItem
import com.ruangaldo.domain.UsdItem

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class LocalItemsMapper {
    companion object {
        fun map(items: List<LocalRootCryptoFeed>): List<CryptoFeedItem> {
            return items.map {
                CryptoFeedItem(
                    coinInfo = CoinInfoItem(
                        it.id,
                        it.name.orEmpty(),
                        it.fullName.orEmpty(),
                        it.imageUrl.orEmpty()
                    ),
                    raw = RawItem(
                        usd = UsdItem(
                            it.price ?: 0.0,
                            it.changePctDay
                        )
                    )
                )
            }
        }
    }
}

