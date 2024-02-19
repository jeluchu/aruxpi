package com.jeluchu.aruxpi.core.utils

import com.jeluchu.aruxpi.models.anime.AiringTime
import com.jeluchu.aruxpi.models.anime.AlternativeTitles
import com.jeluchu.aruxpi.models.anime.AnimeBroadcast
import com.jeluchu.aruxpi.models.anime.AnimeTypes
import com.jeluchu.aruxpi.models.anime.Companies
import com.jeluchu.aruxpi.models.anime.ExternalLinks
import com.jeluchu.aruxpi.models.anime.Related
import com.jeluchu.aruxpi.models.anime.Seasons
import com.jeluchu.aruxpi.models.anime.Themes
import com.jeluchu.aruxpi.models.anime.VideoPromo
import com.jeluchu.jikax.core.models.enums.AnimeType
import com.jeluchu.jikax.core.models.enums.Season
import com.jeluchu.jikax.core.utils.empty
import com.jeluchu.jikax.core.utils.zero
import com.jeluchu.jikax.models.anime.Aired
import com.jeluchu.jikax.models.anime.Anime
import com.jeluchu.jikax.models.anime.Broadcast
import com.jeluchu.jikax.models.anime.Entry
import com.jeluchu.jikax.models.anime.External
import com.jeluchu.jikax.models.anime.Licensor
import com.jeluchu.jikax.models.anime.Producer
import com.jeluchu.jikax.models.anime.Relation
import com.jeluchu.jikax.models.anime.Streaming
import com.jeluchu.jikax.models.anime.Studio
import com.jeluchu.jikax.models.anime.Theme
import com.jeluchu.jikax.models.anime.Title
import com.jeluchu.jikax.models.anime.Trailer

fun Int.Companion.zero() = 0
fun String.Companion.empty() = ""

fun String.toMinutes(): String {
    val regex = """(\d+) min""".toRegex()

    val matchResult = regex.find(this)
    return matchResult?.groupValues?.get(1).orEmpty()
}

fun Trailer.toVideoPromo() = VideoPromo(
    url = url,
    youtubeId = youtubeId
)

fun Season.toSeasons() = when(this) {
    Season.winter -> Seasons.winter
    Season.spring -> Seasons.spring
    Season.summer -> Seasons.summer
    Season.fall -> Seasons.fall
}

fun AnimeType.toType() = when(this) {
    AnimeType.All -> AnimeTypes.All
    AnimeType.TV -> AnimeTypes.TV
    AnimeType.OVA -> AnimeTypes.OVA
    AnimeType.Movie -> AnimeTypes.Movie
    AnimeType.Special -> AnimeTypes.Special
    AnimeType.ONA -> AnimeTypes.ONA
    AnimeType.Music -> AnimeTypes.Music
}

fun External.toExternalLinks() = ExternalLinks(
    url = url,
    name = name
)

fun Streaming.toExternalLinks() = ExternalLinks(
    url = url,
    name = name
)

fun Theme.toThemes() = Themes(
    endings = endings,
    openings = openings
)

fun Studio.toCompanies() = Companies(
    malId = malId,
    name = name,
    type = type,
    url = url
)

fun Licensor.toCompanies() = Companies(
    malId = malId,
    name = name,
    type = type,
    url = url
)

fun Producer.toCompanies() = Companies(
    malId = malId,
    name = name,
    type = type,
    url = url
)

fun Entry.toCompanies() = Companies(
    malId = malId,
    name = name,
    type = type,
    url = url
)

fun Relation.toRelated() = Related(
    entry = entry.map { it.toCompanies() },
    relation = relation,
)

fun Broadcast.toAnimeBroadcast() = AnimeBroadcast(
    day = day,
    time = time,
    timezone = timezone
)

fun Aired.toAiringTime() = AiringTime(
    from = "${prop.from.day}/${prop.from.month}/${prop.from.year}",
    to = "${prop.to.day}/${prop.to.month}/${prop.to.year}"
)

fun Title.toAiringTime() = AlternativeTitles(
    title = title,
    type = type
)