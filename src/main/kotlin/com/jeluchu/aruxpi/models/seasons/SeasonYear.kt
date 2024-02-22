package com.jeluchu.aruxpi.models.seasons

/**
 * Season data class.
 */
data class SeasonYear(
    /**
     * Year for season
     */
    val year: Int,

    /**
     * Seasons in the year
     */
    val seasons: List<String>
)