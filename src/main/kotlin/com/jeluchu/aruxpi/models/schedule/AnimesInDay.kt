package com.jeluchu.aruxpi.models.schedule

import com.jeluchu.monkx.core.utils.empty
import com.jeluchu.monkx.core.utils.zero

data class AnimesInDay(
    val id: Int = Int.zero(),
    val malId: Int = Int.zero(),
    val name: String = String.empty(),
    val image: String = String.empty(),
)