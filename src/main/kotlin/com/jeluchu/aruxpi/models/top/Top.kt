package com.jeluchu.aruxpi.models.top

import com.jeluchu.aruxpi.models.anime.Seasons
import com.jeluchu.aruxpi.models.anime.VideoPromo

data class Top(
    val rank: Int,
    val score: String,
    val malId: Int,
    val title: String,
    val image: String,
    val url: String,
    val promo: VideoPromo,
    val season: Seasons,
    val year: Int,
    val airing: Boolean,
    val type: String,
    val subtype: String,
    val page: Int
)