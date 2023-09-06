package com.ruangaldo.mycrypto.factories

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ruangaldo.presentation.CryptoFeedViewModel

/**
 * Written with joy and smile by Ruang Aldo on 06/09/23.
 * Github: https://github.com/reinaldoriant
 */

class ViewModelFactory {
    companion object {
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CryptoFeedViewModel(
                    CryptoFeedCompositeFactory.createCompositeFactory(
                        primary = CryptoFeedDecoratorFactory.create(
                            decorator = RemoteCryptoFeedLoaderFactory.create(),
                            cache = LocalCryptoFeedInsertFactory.create()
                        ),
                        fallback = LocalCryptoFeedLoaderFactory.create()
                    )
                )
            }
        }
    }
}
