package com.jeluchu.aruxpi.models.anime

open class Staff(
    /**
     * Character generic info
     * @see Individual
     */
    val person: Individual = Individual(),

    /**
     * Request hast next page or not.
     */
    val positions: List<String> = emptyList()
)