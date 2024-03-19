package com.kys2024.tpkysadministration.activities

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication :Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"be2d69899ca880c661e53d8fee3102d0")
    }
}