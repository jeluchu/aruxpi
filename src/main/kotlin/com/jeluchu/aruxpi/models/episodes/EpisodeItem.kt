package com.jeluchu.aruxpi.models.episodes

import com.jeluchu.monkx.models.episodes.Episode


data class EpisodeItem(
    val title: String,
    val episodeNumber: Int,
    val type: String,
    val image: String,
    val url: String,
    val id: String
)

fun Episode.toEpisodeItem() = EpisodeItem(
    title = title,
    episodeNumber = episodeNumber,
    type = type,
    image = image,
    url = url,
    id = id
)