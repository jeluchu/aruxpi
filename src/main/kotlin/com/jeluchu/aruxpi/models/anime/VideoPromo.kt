package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.models.anime.Trailer

open class VideoPromo(
    /**
     * Url for trailer.
     */
    val url: String = String.empty(),

    /**
     * Youtube id for trailer.
     */
    val youtubeId: String = String.empty()
)