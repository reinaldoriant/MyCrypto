package com.ruangaldo.decorator.factories

import com.ruangaldo.http.CryptoFeedHttpClient
import com.ruangaldo.http.CryptoFeedRetrofitHttpClient

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedHttpClientFactory {
    companion object {
        fun createCryptoFeedHttpClient(): CryptoFeedHttpClient {
            return CryptoFeedRetrofitHttpClient(
                CryptoFeedServiceFactory.createCryptoFeedService()
            )
        }
    }
}
