package com.jeluchu.aruxpi.models.anime

import com.jeluchu.aruxpi.core.utils.empty
import com.jeluchu.aruxpi.core.utils.zero

data class AnimeInfoEntity(
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
    var status: String = String.empty(),
    var type: AnimeTypes = AnimeTypes.All,
    val url: String = String.empty(),
    val promo: VideoPromo = VideoPromo(),
    val source: String = String.empty(),
    val duration: String = String.empty(),
    val rank: Int = Int.zero(),
    val titles: List<AlternativeTitles> = emptyList(),
    val airing: Boolean = false,
    val aired: AiringTime = AiringTime(),
    val broadcast: AnimeBroadcast = AnimeBroadcast(),
    val season: Seasons = Seasons.winter,
    val year: Int = Int.zero(),
    val external: List<ExternalLinks> = emptyList(),
    val streaming: List<ExternalLinks> = emptyList(),
    val studios: List<Companies> = emptyList(),
    val licensors: List<Companies> = emptyList(),
    val producers: List<Companies> = emptyList(),
    val theme: Themes = Themes(),
    val relations: List<Related> = emptyList()
)