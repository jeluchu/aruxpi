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
    var id: String = String.empty(),

    /**
     * Episode number.
     */
    var number: Int = Int.zero(),

    /**
     * Date for next episode.
     */
    var nextEpisodeDate: String = String.empty()
)