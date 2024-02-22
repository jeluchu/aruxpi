package com.jeluchu.aruxpi

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class AnimeInfoTests {
    @Test
    fun `on getAnime to return all information of anime`() {
        val result = runBlocking { Aruxpi.getAnime("Steins;Gate") }
        assertTrue(result.malId != 0)
        assertTrue(result.title.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getAnime with malId and name to return all information of anime`() {
        val result = runBlocking { Aruxpi.getAnime(10798, "Un-Go") }
        assertTrue(result.malId != 0)
        assertTrue(result.title.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getAnime with malId and name to return information of anime for Jikan`() {
        val result = runBlocking { Aruxpi.getAnime(54871, "Shin Nippon History") }
        assertTrue(result.malId != 0)
        assertTrue(result.title.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getRandomAnime return random information of anime`() {
        val result = runBlocking { Aruxpi.getRandomAnime() }
        assertTrue(result.malId != 0)
        assertTrue(result.title.isNotEmpty())
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