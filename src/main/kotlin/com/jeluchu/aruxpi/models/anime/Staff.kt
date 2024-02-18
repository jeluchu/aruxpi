package com.jeluchu.aruxpi.models.anime

open class Staff(
    /**
     * Character generic info
     * @see Individual
     */
    var person: Individual = Individual(),

    /**
     * Request hast next page or not.
     */
    var positions: List<String> = emptyList()
)