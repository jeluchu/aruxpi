package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

class AnimeBroadcast(
    /**
     * Day in broadcast.
     */
    val day: String = String.empty(),

    /**
     * Time date in broadcast.
     */
    val time: String = String.empty(),

    /**
     * Timezone in broadcast.
     */
    val timezone: String = String.empty()
) {
    companion object {
        fun AnimeBroadcast?.orEmpty() = this ?: empty()

        fun empty() =
            AnimeBroadcast(
                String.empty(),
                String.empty(),
                String.empty()
            )
    }
}