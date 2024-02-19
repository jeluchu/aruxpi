package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.models.anime.Entry
import com.jeluchu.jikax.models.anime.Trailer

open class Related(
    /**
     * List of entries for relation in anime.
     * @see Companies
     */
    val entry: List<Companies>,

    /**
     * Relation for anime.
     */
    val relation: String = String.empty()
)