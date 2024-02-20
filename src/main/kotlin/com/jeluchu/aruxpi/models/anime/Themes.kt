package com.jeluchu.aruxpi.models.anime

open class Themes(
    /**
     * List of endings.
     */
    val endings: List<String> = emptyList(),

    /**
     * List of openings.
     */
    val openings: List<String> = emptyList()
) {
    companion object {
        fun Themes?.orEmpty() = this ?: empty()

        fun empty() =
            Themes(
                emptyList(),
                emptyList()
            )
    }
}