package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.models.anime.Trailer

/**
 * AlternativeTitles data class.
 */
open class AlternativeTitles(
    /**
     * Title for anime.
     */
    val title: String = String.empty(),

    /**
     * Title type for anime.
     */
    val type: String = String.empty()
)