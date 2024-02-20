package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

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