package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

open class Actor(
    /**
     * Person generic info.
     * @see Individual
     */
    val person: Individual = Individual(),

    /**
     * Language of the person.
     */
    val language: String = String.empty()
)