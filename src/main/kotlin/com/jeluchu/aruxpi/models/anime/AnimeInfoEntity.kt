package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.utils.empty
import com.jeluchu.aruxpi.core.utils.zero

open class AnimeInfoEntity(
    var malId: Int = Int.zero(),
    var title: String = String.empty(),
    var poster: String = String.empty(),
    var cover: String = String.empty(),
    var genres: List<String> = emptyList(),
    var synopsis: String = String.empty(),
    var episodes: List<Episode> = emptyList(),
    var episodesCount: Int = Int.zero(),
    var score: String = String.empty(),
    var staff: List<Staff> = emptyList(),
    var characters: List<Character> = emptyList(),
    //val moreInfo: List<MoreInfo> = emptyList(),
    //val promo: List<Promo> = emptyList(),
    var status: String = String.empty(),
    var type: String = String.empty(),
    //val related: List<Related>?,
)