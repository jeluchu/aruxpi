package com.jeluchu.aruxpi.models.anime

open class Related(
    /**
     * List of entries for relation in anime.
     * @see Companies
     */
    val entry: List<Companies>,

    /**
     * Relation for anime.
     */
    val relation: String
)