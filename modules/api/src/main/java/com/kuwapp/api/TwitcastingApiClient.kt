package com.kuwapp.api

import retrofit2.Retrofit

interface TwitcastingApiClient {

}

internal class TwitcastingApiClientImpl(private val retrofit: Retrofit) : TwitcastingApiClient {

    private val service = retrofit.create(TwitcastingService::class.java)

    private interface TwitcastingService {

    }

}