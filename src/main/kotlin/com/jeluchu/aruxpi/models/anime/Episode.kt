package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.zero
import com.jeluchu.aruxpi.core.utils.empty

/**
 * Episode data class.
 */
open class Episode(
    /**
     * Id for episode.
     */
    val id: String = String.empty(),

    /**
     * Episode number.
     */
    val number: Int = Int.zero(),

    /**
     * Date for next episode.
     */
    val nextEpisodeDate: String = String.empty()
)