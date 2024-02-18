package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.utils.empty
import com.jeluchu.aruxpi.core.utils.zero

open class AnimeInfoEntity(
    open var malId: Int = Int.zero(),
    open var title: String = String.empty(),
    open var poster: String = String.empty(),
    open var cover: String = String.empty(),
    open var genres: List<String> = emptyList(),
    open var synopsis: String = String.empty(),
    open var episodes: List<Episode> = emptyList(),
    open var episodesCount: Int = Int.zero(),
    open var score: String = String.empty(),
    open var staff: List<Staff> = emptyList(),
    open var characters: List<Character> = emptyList(),
    //val moreInfo: List<MoreInfo> = emptyList(),
    //val promo: List<Promo> = emptyList(),
    open var status: String = String.empty(),
    open var type: String = String.empty(),
    //val related: List<Related>?,
)