package com.kuwapp.api

import android.util.Base64

interface TwitcastingAuth {

    fun base64ClientIdSecret(): String?

    fun accessToken(): String?

}

internal class TwitcastingAuthImpl(private val clientId: String,
                                   private val clientSecret: String,
                                   private val createAccessToken: () -> String?)
    : TwitcastingAuth {

    override fun base64ClientIdSecret(): String? {
        val clientIdAndSecret = "$clientId:$clientSecret"
        return Base64.encodeToString(clientIdAndSecret.toByteArray(), Base64.NO_WRAP)
    }

    override fun accessToken(): String? = createAccessToken()

}