package com.ruangaldo.mycrypto.factories

import com.ruangaldo.mycrypto.http.usecases.RemoteCryptoFeedLoader
import com.ruangaldo.mycrypto.domain.CryptoFeedLoader

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class RemoteCryptoFeedLoaderFactory {
    companion object {
        fun createRemoteCryptoFeedLoader(): CryptoFeedLoader {
            return RemoteCryptoFeedLoader(
                CryptoFeedHttpClientFactory.createCryptoFeedHttpClient()
            )
        }
    }
}
