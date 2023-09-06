package com.ruangaldo.decorator.composite

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CompositeCryptoFeedLoader(
    private val primary: com.ruangaldo.domain.CryptoFeedLoader,
    private val fallback: com.ruangaldo.domain.CryptoFeedLoader
): com.ruangaldo.domain.CryptoFeedLoader {
    override fun load(): Flow<com.ruangaldo.domain.CryptoFeedResult> {
        return flow {
            primary.load().collect {
                when (it) {
                    is com.ruangaldo.domain.CryptoFeedResult.Success -> emit(it)
                    is com.ruangaldo.domain.CryptoFeedResult.Failure -> emit(fallback.load().first())
                }
            }
        }
    }
}
