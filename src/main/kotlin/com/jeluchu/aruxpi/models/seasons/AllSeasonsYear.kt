package com.jeluchu.aruxpi.models.seasons

/**
 * AllSeasonsYear data class.
 */
data class AllSeasonsYear(
    /**
     * All animes in winter season.
     */
    val winter: List<AnimeSeason>,

    /**
     * All animes in spring season.
     */
    val spring: List<AnimeSeason>,

    /**
     * All animes in summer season.
     */
    val summer: List<AnimeSeason>,

    /**
     * All animes in fall season.
     */
    val fall: List<AnimeSeason>,
)