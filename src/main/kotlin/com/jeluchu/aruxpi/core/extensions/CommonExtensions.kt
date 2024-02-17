package com.jeluchu.aruxpi.core.extensions

import com.jeluchu.aruxpi.models.schedule.AnimesInDay
import com.jeluchu.jikax.models.anime.AnimeData

fun Int.Companion.zero() = 0
fun String.Companion.empty() = ""

fun AnimeData.toAnimesInDay() = AnimesInDay(
    id = malId,
    malId = malId,
    name = titles?.first()?.title.orEmpty(),
    image = images?.webp?.large.orEmpty()
)