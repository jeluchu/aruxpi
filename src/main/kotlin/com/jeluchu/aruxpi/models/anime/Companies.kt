package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty
import com.jeluchu.aruxpi.core.extensions.zero

data class Companies(
    /**
     * ID associated with MyAnimeList.
     */
    val malId: Int = Int.zero(),

    /**
     * Name for company.
     */
    val name: String = String.empty(),

    /**
     * Type for company.
     */
    val type: String = String.empty(),

    /**
     * Url for company.
     */
    val url: String = String.empty()
)