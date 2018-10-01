package com.kuwapp.api

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

internal class TwitcastingHeaderTest {

    @Test
    fun headers_returnApiVersion2() {
        val clientIdSecret = "clientIdSecret"

        val twitcastingAuth = mock(TwitcastingAuth::class.java)
        `when`(twitcastingAuth.accessToken()).thenReturn(null)
        `when`(twitcastingAuth.base64ClientIdSecret()).thenReturn(clientIdSecret)

        val twitcastingHeader = TwitcastingHeader(twitcastingAuth)
        val headers = twitcastingHeader.headers()
        assertThat(headers.size()).isEqualTo(2)
        assertThat(headers["X-Api-Version"]).isEqualTo("2.0")
    }

    @Test
    fun headers_givenAccessTokenNull_returnAuthorizationIsClientIdSecret() {
        val clientIdSecret = "clientIdSecret"

        val twitcastingAuth = mock(TwitcastingAuth::class.java)
        `when`(twitcastingAuth.accessToken()).thenReturn(null)
        `when`(twitcastingAuth.base64ClientIdSecret()).thenReturn(clientIdSecret)

        val twitcastingHeader = TwitcastingHeader(twitcastingAuth)
        val headers = twitcastingHeader.headers()
        assertThat(headers["Authorization"]).isEqualTo("Basic $clientIdSecret")
    }

    @Test
    fun headers_givenAccessTokenNotNull_returnAuthorizationIsAccessToken() {
        val clientIdSecret = "clientIdSecret"
        val accessToken = "AccessToken"
        val twitcastingAuth = mock(TwitcastingAuth::class.java)
        `when`(twitcastingAuth.accessToken()).thenReturn(accessToken)
        `when`(twitcastingAuth.base64ClientIdSecret()).thenReturn(clientIdSecret)

        val twitcastingHeader = TwitcastingHeader(twitcastingAuth)
        val headers = twitcastingHeader.headers()
        assertThat(headers["Authorization"]).isEqualTo("Bearer $accessToken")
    }

}