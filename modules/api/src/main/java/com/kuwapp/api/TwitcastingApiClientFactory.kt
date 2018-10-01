package com.kuwapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TwitcastingApiClientFactory {

    fun create(clientId: String, clientSecret: String, createAccessToken: () -> String?): TwitcastingApiClient {
        return create(TwitcastingAuthImpl(clientId, clientSecret, createAccessToken))
    }

    fun create(twitcastingAuth: TwitcastingAuth): TwitcastingApiClient {
        val okHttpClient = createOkHttpClient(twitcastingAuth)
        val retrofit = createRetrofit(okHttpClient)
        return TwitcastingApiClientImpl(retrofit)
    }

    private fun createOkHttpClient(twitcastingAuth: TwitcastingAuth): OkHttpClient {
        val header = TwitcastingHeader(twitcastingAuth)
        return OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request().newBuilder()
                            .headers(header.headers())
                            .build()
                    it.proceed(request)
                }
                .build()
    }

    private fun createRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://apiv2.twitcasting.tv")
                .client(client)
                .build()
    }

}
