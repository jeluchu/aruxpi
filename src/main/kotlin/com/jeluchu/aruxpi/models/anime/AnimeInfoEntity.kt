package com.jeluchu.aruxpi.models.anime

import com.jeluchu.monkx.core.utils.empty
import com.jeluchu.monkx.core.utils.zero

data class AnimeInfoEntity(
    val malId: Int = Int.zero(),
    val title: String = String.empty(),
    val poster: String = String.empty(),
    val cover: String = String.empty(),
    val genres: List<String> = emptyList(),
    val synopsis: String = String.empty(),
    val episodes: List<Episode> = emptyList(),
    val episodesCount: Int = Int.zero(),
    val score: String = String.empty(),

    //val characters: List<Character> = emptyList(),
    //val moreInfo: List<MoreInfo> = emptyList(),
    //val promo: List<Promo> = emptyList(),
    val status: String = String.empty(),
    val type: String = String.empty(),
    //val related: List<Related>?,
)