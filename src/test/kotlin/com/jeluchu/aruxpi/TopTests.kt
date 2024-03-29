package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.core.enums.Ratings
import com.jeluchu.aruxpi.core.enums.TopStates
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TopTests {
    @Test
    fun `on getTop pass an anime type and filter return 25 anime top`() {
        val filter = TopStates.airing
        val result = runBlocking { Aruxpi.getAnimeTop(filter) }
        assertEquals(25, result.size)
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getTop pass upcoming and filter return 25 anime top`() {
        val filter = TopStates.upcoming
        val result = runBlocking { Aruxpi.getAnimeTop(filter) }
        assertEquals(25, result.size)
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getTop pass bypopularity and filter return 25 anime top`() {
        val filter = TopStates.bypopularity
        val result = runBlocking { Aruxpi.getAnimeTop(filter) }
        assertEquals(25, result.size)
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getTop pass favorite and filter return 25 anime top`() {
        val filter = TopStates.favorite
        val result = runBlocking { Aruxpi.getAnimeTop(filter) }
        assertEquals(25, result.size)
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getAllAnimeTop params and return all animes in top`() {
        val filter = TopStates.airing
        val result = runBlocking { Aruxpi.getAllAnimeTop(filter) }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getAllAnimeTop filter bypopularity and return all animes in top`() {
        val filter = TopStates.favorite
        val result = runBlocking { Aruxpi.getAllAnimeTop(filter) }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getTop pass an anime type, filter and rating return 25 anime top`() {
        val filter = TopStates.airing
        val rating = Ratings.pg13
        val result = runBlocking { Aruxpi.getAnimeTop(filter, rating) }
        assertEquals(25, result.size)
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