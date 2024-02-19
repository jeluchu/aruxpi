package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.Aruxpi
import com.jeluchu.jikax.models.schedule.Day
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ScheduleTests {
    @Test
    fun `on getWeek to return all animes in the week by day`() {
        val result = runBlocking { Aruxpi.getWeek() }
        assertTrue(result.monday.isNotEmpty())
        assertTrue(result.tuesday.isNotEmpty())
        assertTrue(result.wednesday.isNotEmpty())
        assertTrue(result.thursday.isNotEmpty())
        assertTrue(result.friday.isNotEmpty())
        assertTrue(result.saturday.isNotEmpty())
        assertTrue(result.sunday.isNotEmpty())
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getDay to return all animes in the day`() {
        val result = runBlocking { Aruxpi.getDay(Day.SATURDAY) }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }


    @Test
    fun `on getAnime to return all animes in the day`() {
        val result = runBlocking { Aruxpi.getAnime("Steins;Gate") }
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