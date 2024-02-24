package com.jeluchu.aruxpi

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SeasonTests {
    @Test
    fun `on getAllSeasonNow return list of animes with simple information`() {
        val result = runBlocking { Aruxpi.getAllSeasonNow() }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getSeasons return list of years with all available seasons`() {
        val result = runBlocking { Aruxpi.getSeasons() }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getSeason pass param for year and season return list of all animes in season`() {
        val year = 2013
        val season = "fall"
        val result = runBlocking { Aruxpi.getSeason(year, season) }
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