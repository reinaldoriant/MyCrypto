package com.ruangaldo.mycrypto.factories

import com.ruangaldo.http.usecases.RemoteCryptoFeedLoader

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class RemoteCryptoFeedLoaderFactory {
    companion object {
        fun create(): com.ruangaldo.domain.CryptoFeedLoader {
            return RemoteCryptoFeedLoader(
                CryptoFeedHttpClientFactory.createCryptoFeedHttpClient()
            )
        }
    }
}
