package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

open class Character(
    /**
     * Character generic info
     * @see Individual
     */
    val character: Individual = Individual(),

    /**
     * Role of the character.
     */
    val role: String = String.empty(),

    /**
     * Role of the character.
     */
    val voiceActor: List<Actor> = emptyList()
)