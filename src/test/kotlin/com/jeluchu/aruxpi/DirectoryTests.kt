package com.jeluchu.aruxpi

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class DirectoryTests {
    @Test
    fun `on getDirectory return all anime directory`() {
        val result = runBlocking { Aruxpi.getDirectory() }
        assertTrue(result.lastUpdate.isNotEmpty())
        assertTrue(result.data.isNotEmpty())
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