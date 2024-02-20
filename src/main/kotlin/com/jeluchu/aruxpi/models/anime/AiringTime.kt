package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

open class AiringTime(
    /**
     * Start date airing.
     */
    val from: String = String.empty(),

    /**
     * End date airing.
     */
    val to: String = String.empty()
) {
    companion object {
        fun AiringTime?.orEmpty() = this ?: empty()

        fun empty() =
            AiringTime(
                String.empty(),
                String.empty()
            )
    }
}