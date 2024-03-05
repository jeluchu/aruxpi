package com.jeluchu.aruxpi

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ImagesTests {
    @Test
    fun `on getImages pass an query and images`() {
        val query = "Steins;Gate"
        val result = runBlocking { Aruxpi.getImages(query) }
        assertTrue(result.images.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getImages pass an query with limit and images`() {
        val query = "Steins;Gate"
        val limit = 100
        val result = runBlocking { Aruxpi.getImages(query = query, limit = limit) }
        assertTrue(result.images.isNotEmpty())
        runBlocking { delay(3000) }
    }

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun setup() {
            Aruxpi
        }
    }
}