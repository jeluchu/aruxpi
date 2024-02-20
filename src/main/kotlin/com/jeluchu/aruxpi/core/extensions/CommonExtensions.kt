package com.jeluchu.aruxpi.core.extensions

fun Int.Companion.zero() = 0
fun String.Companion.empty() = ""
fun Int?.orZero(): Int = this ?: 0
fun Float?.orZero(): Float = this ?: 0f

fun String.toMinutes(): String {
    val regex = """(\d+) min""".toRegex()

    val matchResult = regex.find(this)
    return matchResult?.groupValues?.get(1).orEmpty()
}