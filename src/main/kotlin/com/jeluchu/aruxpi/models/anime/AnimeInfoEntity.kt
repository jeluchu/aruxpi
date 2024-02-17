package com.jeluchu.aruxpi.models.anime

import com.jeluchu.jikax.models.character.CharacterInfo
import com.jeluchu.jikax.models.staff.StaffInfo
import com.jeluchu.monkx.core.utils.empty
import com.jeluchu.monkx.core.utils.zero
import com.jeluchu.monkx.models.anime.AnimeEpisode

open class AnimeInfoEntity(
    val malId: Int = Int.zero(),
    val title: String = String.empty(),
    val poster: String = String.empty(),
    val cover: String = String.empty(),
    val genres: List<String> = emptyList(),
    val synopsis: String = String.empty(),
    val episodes: List<AnimeEpisode> = emptyList(),
    val episodesCount: Int = Int.zero(),
    val score: String = String.empty(),
    val staff: List<StaffInfo> = emptyList(),
    val characters: List<CharacterInfo> = emptyList(),
    //val moreInfo: List<MoreInfo> = emptyList(),
    //val promo: List<Promo> = emptyList(),
    val status: String = String.empty(),
    val type: String = String.empty(),
    //val related: List<Related>?,
)