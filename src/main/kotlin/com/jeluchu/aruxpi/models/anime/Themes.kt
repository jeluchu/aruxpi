package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.models.anime.Trailer

open class Themes(
    /**
     * List of endings.
     */
    val endings: List<String> = emptyList(),

    /**
     * List of openings.
     */
    val openings: List<String> = emptyList()
)