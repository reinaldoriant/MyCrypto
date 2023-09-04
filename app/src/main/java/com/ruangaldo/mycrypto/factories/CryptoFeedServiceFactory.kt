package com.ruangaldo.mycrypto.factories

import com.ruangaldo.mycrypto.frameworks.HttpFactory
import com.ruangaldo.mycrypto.http.CryptoFeedService

/**
 * Written with joy and smile by Ruang Aldo on 04/09/23.
 * Github: https://github.com/reinaldoriant
 */

class CryptoFeedServiceFactory {
    companion object {
        fun createCryptoFeedService(): CryptoFeedService {
            return HttpFactory.createRetrofit(
                HttpFactory.createMoshi(),
                HttpFactory.createOkhttpClient(
                    HttpFactory.createLoggingInterceptor()

                )
            ).create(CryptoFeedService::class.java)
        }
    }

}
