package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.core.enums.Sources
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class EpisodesTests {
    @Test
    fun `on getLastEpisodes return list of last animes`() {
        val result = runBlocking { Aruxpi.getLastEpisodes() }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getTop pass an anime type, filter and rating return 25 anime top`() {
        val id = "metallic-rouge-episodio-7"
        val result = runBlocking { Aruxpi.getServers(id, Sources.Monkx) }
        assertTrue(result.isNotEmpty())
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