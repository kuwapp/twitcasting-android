package com.kuwapp.api

import okhttp3.Headers

internal class TwitcastingHeader(private val twitcastingAuth: TwitcastingAuth) {

    fun headers(): Headers {
        val accessToken = twitcastingAuth.accessToken()
        val authorizationValue = if (accessToken != null) {
            "Bearer $accessToken"
        } else {
            "Basic ${twitcastingAuth.base64ClientIdSecret()}"
        }
        return Headers.of(mapOf(
                Pair("X-Api-Version", BuildConfig.API_VERSION),
                Pair("Authorization", authorizationValue)
        ))
    }

}