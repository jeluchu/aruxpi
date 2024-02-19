package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.core.extensions.toAnimesInDay
import com.jeluchu.aruxpi.core.extensions.toCharacter
import com.jeluchu.aruxpi.core.extensions.toEpisode
import com.jeluchu.aruxpi.core.extensions.toStaff
import com.jeluchu.aruxpi.core.utils.toAiringTime
import com.jeluchu.aruxpi.core.utils.toAnimeBroadcast
import com.jeluchu.aruxpi.core.utils.toCompanies
import com.jeluchu.aruxpi.core.utils.toExternalLinks
import com.jeluchu.aruxpi.core.utils.toMinutes
import com.jeluchu.aruxpi.core.utils.toRelated
import com.jeluchu.aruxpi.core.utils.toSeasons
import com.jeluchu.aruxpi.core.utils.toThemes
import com.jeluchu.aruxpi.core.utils.toType
import com.jeluchu.aruxpi.core.utils.toVideoPromo
import com.jeluchu.aruxpi.extractor.getJikaxInfo
import com.jeluchu.aruxpi.extractor.getMonksInfo
import com.jeluchu.aruxpi.extractor.getTioximeInfo
import com.jeluchu.aruxpi.models.anime.AnimeInfoEntity
import com.jeluchu.aruxpi.models.schedule.AnimesInDay
import com.jeluchu.aruxpi.models.schedule.Week
import com.jeluchu.jikax.Jikax
import com.jeluchu.jikax.models.schedule.Day

object Aruxpi {

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see Week
     */
    suspend fun getWeek(): Week {
        val week = Jikax.getSchedule()
        return Week(
            monday = week.monday.map { it.toAnimesInDay() },
            tuesday = week.tuesday.map { it.toAnimesInDay() },
            wednesday = week.wednesday.map { it.toAnimesInDay() },
            thursday = week.thursday.map { it.toAnimesInDay() },
            friday = week.friday.map { it.toAnimesInDay() },
            saturday = week.saturday.map { it.toAnimesInDay() },
            sunday = week.sunday.map { it.toAnimesInDay() }
        )
    }

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimesInDay
     */
    suspend fun getDay(day: Day): List<AnimesInDay> =
        Jikax.getSchedule(day).map { it.toAnimesInDay() }

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimesInDay
     */
    suspend fun getAnime(name: String): AnimeInfoEntity {
        val monkxInfo = name.getMonksInfo()
        val jikaxInfo = name.getJikaxInfo()

        jikaxInfo?.let { jikax ->
            monkxInfo?.let { monkx ->
                return AnimeInfoEntity(
                    malId = jikax.anime.malId,
                    title = jikax.anime.titles.first { it.type == "Default" }.title,
                    poster = monkx.image,
                    cover = monkx.cover,
                    genres = monkx.genres,
                    synopsis = monkx.synopsis,
                    episodes = monkx.episodes.map { it.toEpisode() },
                    episodesCount = monkx.episodesCount,
                    score = jikax.anime.score.toString(),
                    staff = jikax.staff.map { it.toStaff() },
                    characters = jikax.characters.map { it.toCharacter() },
                    status = jikax.anime.status,
                    type = jikax.anime.type.toType(),
                    url = jikax.anime.url,
                    promo = jikax.anime.trailer.toVideoPromo(),
                    source = jikax.anime.source,
                    duration = jikax.anime.duration.toMinutes(),
                    rank = jikax.anime.rank,
                    titles = jikax.anime.titles.map { it.toAiringTime() },
                    airing = jikax.anime.airing,
                    aired = jikax.anime.aired.toAiringTime(),
                    broadcast = jikax.anime.broadcast.toAnimeBroadcast(),
                    season = jikax.anime.season.toSeasons(),
                    year = jikax.anime.year,
                    external = jikax.anime.external.map { it.toExternalLinks() },
                    streaming = jikax.anime.streaming.map { it.toExternalLinks() },
                    studios = jikax.anime.studios.map { it.toCompanies() },
                    licensors = jikax.anime.licensors.map { it.toCompanies() },
                    producers = jikax.anime.producers.map { it.toCompanies() },
                    theme = jikax.anime.theme.toThemes(),
                    relations = jikax.anime.relations.map { it.toRelated() }
                )
            }

            val tioximeInfo = name.getTioximeInfo()
            tioximeInfo?.let { tioxime ->
                return AnimeInfoEntity(
                    malId = jikax.anime.malId,
                    title = jikax.anime.titles.first { it.type == "Default" }.title,
                    poster = tioxime.image,
                    cover = tioxime.cover,
                    genres = tioxime.genres,
                    synopsis = tioxime.synopsis,
                    episodes = tioxime.episodes.map { it.toEpisode() },
                    episodesCount = tioxime.episodesCount,
                    score = jikax.anime.score.toString(),
                    staff = jikax.staff.map { it.toStaff() },
                    characters = jikax.characters.map { it.toCharacter() },
                    status = jikax.anime.status,
                    type = jikax.anime.type.toType(),
                    url = jikax.anime.url,
                    promo = jikax.anime.trailer.toVideoPromo(),
                    source = jikax.anime.source,
                    duration = jikax.anime.duration.toMinutes(),
                    rank = jikax.anime.rank,
                    titles = jikax.anime.titles.map { it.toAiringTime() },
                    airing = jikax.anime.airing,
                    aired = jikax.anime.aired.toAiringTime(),
                    broadcast = jikax.anime.broadcast.toAnimeBroadcast(),
                    season = jikax.anime.season.toSeasons(),
                    year = jikax.anime.year,
                    external = jikax.anime.external.map { it.toExternalLinks() },
                    streaming = jikax.anime.streaming.map { it.toExternalLinks() },
                    studios = jikax.anime.studios.map { it.toCompanies() },
                    licensors = jikax.anime.licensors.map { it.toCompanies() },
                    producers = jikax.anime.producers.map { it.toCompanies() },
                    theme = jikax.anime.theme.toThemes(),
                    relations = jikax.anime.relations.map { it.toRelated() }
                )
            }

            return AnimeInfoEntity(
                malId = jikax.anime.malId,
                title = jikax.anime.titles.first { it.type == "Default" }.title,
                score = jikax.anime.score.toString(),
                staff = jikax.staff.map { it.toStaff() },
                characters = jikax.characters.map { it.toCharacter() },
                status = jikax.anime.status,
                type = jikax.anime.type.toType(),
                url = jikax.anime.url,
                promo = jikax.anime.trailer.toVideoPromo(),
                source = jikax.anime.source,
                duration = jikax.anime.duration.toMinutes(),
                rank = jikax.anime.rank,
                titles = jikax.anime.titles.map { it.toAiringTime() },
                airing = jikax.anime.airing,
                aired = jikax.anime.aired.toAiringTime(),
                broadcast = jikax.anime.broadcast.toAnimeBroadcast(),
                season = jikax.anime.season.toSeasons(),
                year = jikax.anime.year,
                external = jikax.anime.external.map { it.toExternalLinks() },
                streaming = jikax.anime.streaming.map { it.toExternalLinks() },
                studios = jikax.anime.studios.map { it.toCompanies() },
                licensors = jikax.anime.licensors.map { it.toCompanies() },
                producers = jikax.anime.producers.map { it.toCompanies() },
                theme = jikax.anime.theme.toThemes(),
                relations = jikax.anime.relations.map { it.toRelated() }
            )
        }

        return AnimeInfoEntity()
    }
}