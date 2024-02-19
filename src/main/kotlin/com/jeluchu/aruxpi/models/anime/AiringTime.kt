package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty

open class AiringTime(
    /**
     * Start date airing.
     */
    val from: String = String.empty(),

    /**
     * End date airing.
     */
    val to: String = String.empty()
)