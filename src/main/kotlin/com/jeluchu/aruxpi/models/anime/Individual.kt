package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty
import com.jeluchu.aruxpi.core.extensions.zero

open class Individual(
    /**
     * ID associated with MyAnimeList.
     */
    val malId: Int = Int.zero(),

    /**
     * Link to person in MAL.
     */
    val url: String = String.empty(),

    /**
     * Name of person.
     */
    val name: String = String.empty(),

    /**
     * Request hast next page or not.
     */
    val images: String = String.empty()
) {
    companion object {
        fun Individual?.orEmpty() = this ?: empty()

        fun empty() =
            Individual(
                Int.zero(),
                String.empty(),
                String.empty(),
                String.empty()
            )
    }
}