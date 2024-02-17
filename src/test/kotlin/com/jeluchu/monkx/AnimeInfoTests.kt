package com.jeluchu.monkx

import com.jeluchu.aruxpi.Aruxpi
import com.jeluchu.jikax.models.schedule.Day
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

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun setup() {
            Aruxpi
        }
    }
}