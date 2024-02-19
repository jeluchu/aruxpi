package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.models.anime.Trailer

open class ExternalLinks(
    /**
     * Url for trailer.
     */
    val url: String = String.empty(),

    /**
     * Name of external info.
     */
    val name: String = String.empty()
)