package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

data class Images(
    val generic: String = String.empty(),
    val small: String = String.empty(),
    val medium: String = String.empty(),
    val large: String = String.empty(),
    val maximum: String = String.empty()
) {
    companion object {
        fun Images?.orEmpty() = this ?: empty()

        fun empty() =
            Images(
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty()
            )
    }
}