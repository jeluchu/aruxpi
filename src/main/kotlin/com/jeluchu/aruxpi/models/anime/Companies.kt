package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.core.utils.zero
import com.jeluchu.jikax.models.anime.Trailer

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