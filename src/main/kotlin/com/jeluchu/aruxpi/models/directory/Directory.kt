package com.jeluchu.aruxpi.models.directory

import com.jeluchu.aruxpi.core.extensions.orZero
import com.jeluchu.aruxpi.models.anime.Seasons
import com.jeluchu.jikax.core.models.enums.Season

data class Directory(
    val lastUpdate: String,
    val data: List<Anime>,
) {
    data class Anime(
        val mailId: Int,
        val title: String,
        val episodes: Int,
        val type: String,
        val status: String,
        val image: String,
        val season: Season,
        val otherLinks: List<String>
    )

    data class Season(
        val year: Int,
        val season: String
    )
}

fun DirectoryDb.Source.toAnime(malId: Int) = Directory.Anime(
    mailId = malId,
    title = title.orEmpty(),
    episodes = episodes.orZero(),
    type = type.orEmpty(),
    status = status.orEmpty(),
    image = picture.orEmpty(),
    season = Directory.Season(
        season = animeSeason?.season.orEmpty(),
        year = animeSeason?.year ?: 0
    ),
    otherLinks = sources.orEmpty(),
)