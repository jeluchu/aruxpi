package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.models.anime.Trailer

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
)