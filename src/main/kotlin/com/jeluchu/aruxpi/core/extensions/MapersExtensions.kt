package com.jeluchu.aruxpi.core.extensions

import com.jeluchu.aruxpi.core.enums.Ratings
import com.jeluchu.aruxpi.core.enums.TopStates
import com.jeluchu.aruxpi.models.anime.Actor
import com.jeluchu.aruxpi.models.anime.AiringTime
import com.jeluchu.aruxpi.models.anime.AlternativeTitles
import com.jeluchu.aruxpi.models.anime.AnimeBroadcast
import com.jeluchu.aruxpi.models.anime.AnimeTypes
import com.jeluchu.aruxpi.models.anime.Character
import com.jeluchu.aruxpi.models.anime.Companies
import com.jeluchu.aruxpi.models.anime.Episode
import com.jeluchu.aruxpi.models.anime.ExternalLinks
import com.jeluchu.aruxpi.models.anime.Images
import com.jeluchu.aruxpi.models.anime.Images.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.Individual
import com.jeluchu.aruxpi.models.anime.Individual.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.Related
import com.jeluchu.aruxpi.models.anime.Seasons
import com.jeluchu.aruxpi.models.anime.Staff
import com.jeluchu.aruxpi.models.anime.Themes
import com.jeluchu.aruxpi.models.anime.VideoPromo
import com.jeluchu.aruxpi.models.anime.VideoPromo.Companion.orEmpty
import com.jeluchu.aruxpi.models.episodes.EpisodeServer
import com.jeluchu.aruxpi.models.news.New
import com.jeluchu.aruxpi.models.schedule.AnimesInDay
import com.jeluchu.aruxpi.models.search.AnimeSearch
import com.jeluchu.aruxpi.models.seasons.AnimeSeason
import com.jeluchu.aruxpi.models.seasons.SeasonYear
import com.jeluchu.aruxpi.models.top.Top
import com.jeluchu.jikax.core.models.common.ImageFormat
import com.jeluchu.jikax.core.models.enums.AnimeType
import com.jeluchu.jikax.core.models.enums.Rating
import com.jeluchu.jikax.core.models.enums.Season
import com.jeluchu.jikax.core.models.enums.TopFilter
import com.jeluchu.jikax.models.anime.Aired
import com.jeluchu.jikax.models.anime.AnimeData
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
import com.jeluchu.jikax.models.character.CharacterInfo
import com.jeluchu.jikax.models.character.VoiceActor
import com.jeluchu.jikax.models.seasons.SeasonInfo
import com.jeluchu.jikax.models.staff.Person
import com.jeluchu.jikax.models.staff.StaffInfo
import com.jeluchu.monkx.models.anime.AnimeEpisode
import com.prof18.rssparser.model.RssChannel
import java.util.Calendar

fun AnimeData.toAnimesInDay() = AnimesInDay(
    id = malId.orZero(),
    malId = malId.orZero(),
    name = titles?.first()?.title.orEmpty(),
    image = images?.webp?.large.orEmpty()
)

fun AnimeData.toAnimeSeason() = AnimeSeason(
    malId = malId.orZero(),
    title = titles?.first { it.type == "Default" }?.title.orEmpty(),
    image = images?.webp?.large.orEmpty(),
    url = url.orEmpty(),
    season = season?.name.orEmpty(),
    year = year ?: Calendar.getInstance().weekYear
)

fun AnimeData.toAnimeSeason(
    year: Int,
    season: String
) = AnimeSeason(
    malId = malId.orZero(),
    title = titles?.first { it.type == "Default" }?.title.orEmpty(),
    image = images?.webp?.large.orEmpty(),
    url = url.orEmpty(),
    season = season,
    year = year
)

fun AnimeData.toAnimeSearch() = AnimeSearch(
    malId = malId.orZero(),
    title = titles?.first { it.type == "Default" }?.title.orEmpty(),
    image = images?.webp?.large.orEmpty(),
    url = url.orEmpty()
)

fun AnimeEpisode.toEpisode() = Episode(
    id = id,
    number = number,
    nextEpisodeDate = String.empty()
)

fun com.jeluchu.tioxime.models.anime.AnimeEpisode.toEpisode() = Episode(
    id = id,
    number = number,
    nextEpisodeDate = String.empty()
)

fun StaffInfo.toStaff() = Staff(
    person = person?.toIndividual().orEmpty(),
    positions = positions.orEmpty()
)

fun CharacterInfo.toCharacter() = Character(
    character = character?.toIndividual().orEmpty(),
    role = role.orEmpty(),
    voiceActor = voiceActor?.map { it.toActor() }.orEmpty()
)

fun Person.toIndividual() = Individual(
    malId = malId.orZero(),
    url = url.orEmpty(),
    name = name.orEmpty(),
    images = images?.jpg?.generic.orEmpty()
)

fun VoiceActor.toActor() = Actor(
    person = person?.toIndividual().orEmpty(),
    language = language.orEmpty()
)

fun Trailer.toVideoPromo() = VideoPromo(
    url = url.orEmpty(),
    youtubeId = youtubeId.orEmpty(),
    embedUrl = embedUrl.orEmpty(),
    images = images?.toImages().orEmpty()
)

fun ImageFormat.toImages() = Images(
    generic = generic.orEmpty(),
    small = small.orEmpty(),
    medium = medium.orEmpty(),
    large = large.orEmpty(),
    maximum = maximum.orEmpty()
)

fun Season?.toSeasons() = when(this) {
    Season.winter -> Seasons.winter
    Season.spring -> Seasons.spring
    Season.summer -> Seasons.summer
    Season.fall -> Seasons.fall
    else -> Seasons.winter
}

fun AnimeType?.toType() = when(this) {
    AnimeType.All -> AnimeTypes.All
    AnimeType.TV -> AnimeTypes.TV
    AnimeType.OVA -> AnimeTypes.OVA
    AnimeType.Movie -> AnimeTypes.Movie
    AnimeType.Special -> AnimeTypes.Special
    AnimeType.ONA -> AnimeTypes.ONA
    AnimeType.Music -> AnimeTypes.Music
    else  -> AnimeTypes.All
}

fun Ratings?.toRating() = when(this) {
    Ratings.g -> Rating.g
    Ratings.pg -> Rating.pg
    Ratings.pg13 -> Rating.pg13
    Ratings.r17 -> Rating.r17
    Ratings.r -> Rating.r
    Ratings.rx -> Rating.rx
    else -> null
}

fun TopStates?.toTopFilter() = when(this) {
    TopStates.airing -> TopFilter.airing
    TopStates.upcoming  -> TopFilter.upcoming
    TopStates.bypopularity -> TopFilter.bypopularity
    TopStates.favorite -> TopFilter.favorite
    else -> null
}

fun External.toExternalLinks() = ExternalLinks(
    url = url.orEmpty(),
    name = name.orEmpty()
)

fun Streaming.toExternalLinks() = ExternalLinks(
    url = url.orEmpty(),
    name = name.orEmpty()
)

fun Theme.toThemes() = Themes(
    endings = endings.orEmpty(),
    openings = openings.orEmpty()
)

fun Studio.toCompanies() = Companies(
    malId = malId.orZero(),
    name = name.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty()
)

fun Licensor.toCompanies() = Companies(
    malId = malId.orZero(),
    name = name.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty()
)

fun Producer.toCompanies() = Companies(
    malId = malId.orZero(),
    name = name.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty()
)

fun Entry.toCompanies() = Companies(
    malId = malId.orZero(),
    name = name.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty()
)

fun Relation.toRelated() = Related(
    entry = entry?.map { it.toCompanies() }.orEmpty(),
    relation = relation.orEmpty()
)

fun Broadcast.toAnimeBroadcast() = AnimeBroadcast(
    day = day.orEmpty(),
    time = time.orEmpty(),
    timezone = timezone.orEmpty()
)

fun Aired.toAiringTime() = AiringTime(
    from = "${prop?.from?.day}/${prop?.from?.month}/${prop?.from?.year}",
    to = "${prop?.to?.day}/${prop?.to?.month}/${prop?.to?.year}"
)

fun Title.toAiringTime() = AlternativeTitles(
    title = title.orEmpty(),
    type = type.orEmpty()
)

fun AnimeData.toTopTime(
    rank: Int,
    type: String,
    subtype: String,
    page: Int
) = Top(
    rank = rank,
    score = score.orZero().toString(),
    malId = malId.orZero(),
    title = titles?.first { it.type == "Default" }?.title.orEmpty(),
    image = images?.webp?.large.orEmpty(),
    url = url.orEmpty(),
    promo = trailer?.toVideoPromo().orEmpty(),
    season = season.toSeasons(),
    year = year ?: Calendar.getInstance().weekYear,
    airing = airing ?: false,
    type = type,
    subtype = subtype,
    page = page,
)

fun SeasonInfo.toAiringTime() = SeasonYear(
    year = year ?: Calendar.getInstance().weekYear,
    seasons = seasons.orEmpty()
)

fun com.jeluchu.monkx.models.servers.Server.toEpisodeServer() = EpisodeServer(
    id = id,
    url = url
)

fun com.jeluchu.tioxime.models.servers.Server.toEpisodeServer() = EpisodeServer(
    id = id,
    url = url
)

fun RssChannel.toNews(
    source: String,
    list: MutableList<New>
) = list.apply{
    items.forEach { item ->
        add(
            New(
                source = source,
                sourceDescription = description.orEmpty(),
                link = item.link.orEmpty(),
                title = item.title.orEmpty(),
                date = item.pubDate.orEmpty(),
                description = item.description.orEmpty(),
                content = item.content.orEmpty(),
                image = item.image.orEmpty(),
                categories = item.categories,
            )
        )
    }
}
