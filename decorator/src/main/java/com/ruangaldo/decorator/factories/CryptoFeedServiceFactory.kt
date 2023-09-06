package com.ruangaldo.decorator.factories

import com.ruangaldo.http.CryptoFeedService
import com.ruangaldo.http.framework.HttpFactory

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
