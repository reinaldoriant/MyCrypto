package com.ruangaldo.mycrypto.crypto.feed.composite.usecase

import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedLoader
import com.ruangaldo.mycrypto.crypto.feed.domain.CryptoFeedResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CompositeCryptoFeedLoader(
    private val primary: CryptoFeedLoader,
    private val fallback: CryptoFeedLoader
): CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> {
        return flow {
            primary.load().collect {
                when (it) {
                    is CryptoFeedResult.Success -> emit(it)
                    is CryptoFeedResult.Failure -> emit(fallback.load().first())
                }
            }
        }
    }
}
