package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

open class Character(
    /**
     * Character generic info
     * @see Individual
     */
    var character: Individual = Individual(),

    /**
     * Role of the character.
     */
    var role: String = String.empty(),

    /**
     * Role of the character.
     */
    var voiceActor: List<Actor> = emptyList()
)