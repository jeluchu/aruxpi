package com.jeluchu.aruxpi.core.client

import com.jeluchu.jikax.core.connection.RateLimitInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.Protocol

open class AruxpiClient {
    protected val httpClient by lazy {
        HttpClient(OkHttp) {
            engine {
                config {
                    protocols(listOf(Protocol.HTTP_1_1))
                    retryOnConnectionFailure(true)
                }
                addInterceptor(RateLimitInterceptor())
            }

            expectSuccess = false
        }
    }
}