package com.jeluchu.aruxpi.core.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun Int.Companion.zero() = 0
fun String.Companion.empty() = ""
fun Int?.orZero(): Int = this ?: 0
fun Float?.orZero(): Float = this ?: 0f

fun String.toMinutes(): String {
    val regex = """(\d+) min""".toRegex()

    val matchResult = regex.find(this)
    return matchResult?.groupValues?.get(1).orEmpty()
}

fun getMalId(urlBase: String, urls: List<String>): Int? {
    for (url in urls) {
        if (url.startsWith(urlBase)) return url.filter { it.isDigit() }.toInt()
    }
    return null
}

fun String.parseRssDate(format: String = "dd/MM/yyyy"): String {
    val date = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US).parse(this)
    val outputDateFormat = SimpleDateFormat(format, Locale.US)
    return date?.let { outputDateFormat.format(it) }.orEmpty()
}