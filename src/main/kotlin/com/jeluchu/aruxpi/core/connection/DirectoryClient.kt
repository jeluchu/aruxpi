package com.jeluchu.aruxpi.core.connection

import com.google.gson.Gson
import com.jeluchu.aruxpi.core.client.AruxpiClient
import com.jeluchu.aruxpi.models.directory.DirectoryDb
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.headers

class DirectoryClient : AruxpiClient() {
    private val gson = Gson()
    private val client = httpClient

    suspend fun request(): DirectoryDb {
        runCatching {
            val response = client.get(BASE_URL) {
                headers {
                    accept(ContentType("text", "plain"))
                }
            }

            return gson.fromJson(response.body<String>(), DirectoryDb::class.java)
        }.getOrElse { throwable -> throw throwable }
    }

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/jeluchu/anime-offline-database/master/anime-offline-database.json"
    }
}