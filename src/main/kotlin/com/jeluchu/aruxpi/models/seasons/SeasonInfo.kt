package com.jeluchu.aruxpi.models.seasons

import com.jeluchu.jikax.models.search.Pagination

/**
 * SeasonInfo data class.
 */
data class SeasonInfo(
    /**
     * Pagination info for request
     */
    val pagination: Pagination,

    /**
     * Data list of all anime found.
     */
    val data: List<SeasonYear>
)