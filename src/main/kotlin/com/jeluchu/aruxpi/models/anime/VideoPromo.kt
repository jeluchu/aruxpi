package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.extensions.empty

open class VideoPromo(
    /**
     * Embed url for trailer.
     */
    val embedUrl: String = String.empty(),

    /**
     * Url for trailer.
     */
    val url: String = String.empty(),

    /**
     * Youtube id for trailer.
     */
    val youtubeId: String = String.empty(),

    /**
     * Images for trailer.
     */
    val images: Images = Images()
) {
    companion object {
        fun VideoPromo?.orEmpty() = this ?: empty()

        fun empty() =
            VideoPromo(
                String.empty(),
                String.empty(),
                String.empty(),
                Images.empty()
            )
    }
}