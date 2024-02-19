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

        monkxInfo?.let { monkx ->
            return AnimeInfoEntity(
                malId = jikaxInfo.anime.malId,
                title = jikaxInfo.anime.titles.first { it.type == "Default" }.title,
                poster = monkx.image,
                cover = monkx.cover,
                genres = monkx.genres,
                synopsis = monkx.synopsis,
                episodes = monkx.episodes.map { it.toEpisode() },
                episodesCount = monkx.episodesCount,
                score = jikaxInfo.anime.score.toString(),
                staff = jikaxInfo.staff.map { it.toStaff() },
                characters = jikaxInfo.characters.map { it.toCharacter() },
                status = jikaxInfo.anime.status,
                type = jikaxInfo.anime.type.toType(),
                url = jikaxInfo.anime.url,
                promo = jikaxInfo.anime.trailer.toVideoPromo(),
                source = jikaxInfo.anime.source,
                duration = jikaxInfo.anime.duration.toMinutes(),
                rank = jikaxInfo.anime.rank,
                titles = jikaxInfo.anime.titles.map { it.toAiringTime() },
                airing = jikaxInfo.anime.airing,
                aired = jikaxInfo.anime.aired.toAiringTime(),
                broadcast = jikaxInfo.anime.broadcast.toAnimeBroadcast(),
                season = jikaxInfo.anime.season.toSeasons(),
                year = jikaxInfo.anime.year,
                external = jikaxInfo.anime.external.map { it.toExternalLinks() },
                streaming = jikaxInfo.anime.streaming.map { it.toExternalLinks() },
                studios = jikaxInfo.anime.studios.map { it.toCompanies() },
                licensors = jikaxInfo.anime.licensors.map { it.toCompanies() },
                producers = jikaxInfo.anime.producers.map { it.toCompanies() },
                theme = jikaxInfo.anime.theme.toThemes(),
                relations = jikaxInfo.anime.relations.map { it.toRelated() }
            )
        }

        val tioximeInfo = name.getTioximeInfo()
        tioximeInfo?.let { tioxime ->
            return AnimeInfoEntity(
                malId = jikaxInfo.anime.malId,
                title = jikaxInfo.anime.titles.first { it.type == "Default" }.title,
                poster = tioxime.image,
                cover = tioxime.cover,
                genres = tioxime.genres,
                synopsis = tioxime.synopsis,
                episodes = tioxime.episodes.map { it.toEpisode() },
                episodesCount = tioxime.episodesCount,
                score = jikaxInfo.anime.score.toString(),
                staff = jikaxInfo.staff.map { it.toStaff() },
                characters = jikaxInfo.characters.map { it.toCharacter() },
                status = jikaxInfo.anime.status,
                type = jikaxInfo.anime.type.toType(),
                url = jikaxInfo.anime.url,
                promo = jikaxInfo.anime.trailer.toVideoPromo(),
                source = jikaxInfo.anime.source,
                duration = jikaxInfo.anime.duration.toMinutes(),
                rank = jikaxInfo.anime.rank,
                titles = jikaxInfo.anime.titles.map { it.toAiringTime() },
                airing = jikaxInfo.anime.airing,
                aired = jikaxInfo.anime.aired.toAiringTime(),
                broadcast = jikaxInfo.anime.broadcast.toAnimeBroadcast(),
                season = jikaxInfo.anime.season.toSeasons(),
                year = jikaxInfo.anime.year,
                external = jikaxInfo.anime.external.map { it.toExternalLinks() },
                streaming = jikaxInfo.anime.streaming.map { it.toExternalLinks() },
                studios = jikaxInfo.anime.studios.map { it.toCompanies() },
                licensors = jikaxInfo.anime.licensors.map { it.toCompanies() },
                producers = jikaxInfo.anime.producers.map { it.toCompanies() },
                theme = jikaxInfo.anime.theme.toThemes(),
                relations = jikaxInfo.anime.relations.map { it.toRelated() }
            )
        }

        return AnimeInfoEntity(
            malId = jikaxInfo.anime.malId,
            title = jikaxInfo.anime.titles.first { it.type == "Default" }.title,
            score = jikaxInfo.anime.score.toString(),
            staff = jikaxInfo.staff.map { it.toStaff() },
            characters = jikaxInfo.characters.map { it.toCharacter() },
            status = jikaxInfo.anime.status,
            type = jikaxInfo.anime.type.toType(),
            url = jikaxInfo.anime.url,
            promo = jikaxInfo.anime.trailer.toVideoPromo(),
            source = jikaxInfo.anime.source,
            duration = jikaxInfo.anime.duration.toMinutes(),
            rank = jikaxInfo.anime.rank,
            titles = jikaxInfo.anime.titles.map { it.toAiringTime() },
            airing = jikaxInfo.anime.airing,
            aired = jikaxInfo.anime.aired.toAiringTime(),
            broadcast = jikaxInfo.anime.broadcast.toAnimeBroadcast(),
            season = jikaxInfo.anime.season.toSeasons(),
            year = jikaxInfo.anime.year,
            external = jikaxInfo.anime.external.map { it.toExternalLinks() },
            streaming = jikaxInfo.anime.streaming.map { it.toExternalLinks() },
            studios = jikaxInfo.anime.studios.map { it.toCompanies() },
            licensors = jikaxInfo.anime.licensors.map { it.toCompanies() },
            producers = jikaxInfo.anime.producers.map { it.toCompanies() },
            theme = jikaxInfo.anime.theme.toThemes(),
            relations = jikaxInfo.anime.relations.map { it.toRelated() }
        )
    }
}