package com.ruangaldo.mycrypto

import android.app.Application
import com.ruangaldo.mycrypto.frameworks.LocalFactory

/**
 * Written with joy and smile by Ruang Aldo on 05/09/23.
 * Github: https://github.com/reinaldoriant
 */

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        LocalFactory.application = this
    }
}
