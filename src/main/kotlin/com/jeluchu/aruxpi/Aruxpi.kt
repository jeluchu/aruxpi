package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.core.extensions.orZero
import com.jeluchu.aruxpi.core.extensions.toAiringTime
import com.jeluchu.aruxpi.core.extensions.toAnimeBroadcast
import com.jeluchu.aruxpi.core.extensions.toAnimesInDay
import com.jeluchu.aruxpi.core.extensions.toCharacter
import com.jeluchu.aruxpi.core.extensions.toCompanies
import com.jeluchu.aruxpi.core.extensions.toEpisode
import com.jeluchu.aruxpi.core.extensions.toExternalLinks
import com.jeluchu.aruxpi.core.extensions.toMinutes
import com.jeluchu.aruxpi.core.extensions.toRelated
import com.jeluchu.aruxpi.core.extensions.toSeasons
import com.jeluchu.aruxpi.core.extensions.toStaff
import com.jeluchu.aruxpi.core.extensions.toThemes
import com.jeluchu.aruxpi.core.extensions.toType
import com.jeluchu.aruxpi.core.extensions.toVideoPromo
import com.jeluchu.aruxpi.extractor.data.getJikaxInfo
import com.jeluchu.aruxpi.extractor.data.getMonksInfo
import com.jeluchu.aruxpi.extractor.data.getTioximeInfo
import com.jeluchu.aruxpi.extractor.data.toAnimeInfoData
import com.jeluchu.aruxpi.models.anime.AiringTime.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.AnimeBroadcast.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.AnimeInfoEntity
import com.jeluchu.aruxpi.models.anime.Themes.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.VideoPromo.Companion.orEmpty
import com.jeluchu.aruxpi.models.schedule.AnimesInDay
import com.jeluchu.aruxpi.models.schedule.Days
import com.jeluchu.aruxpi.models.schedule.Week
import com.jeluchu.jikax.Jikax
import com.jeluchu.jikax.models.schedule.Day
import java.util.Calendar

object Aruxpi {

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see Week
     */
    suspend fun getWeek(): Week {
        val week = Jikax.getSchedule()
        return Week(
            monday = week.monday?.map { it.toAnimesInDay() }.orEmpty(),
            tuesday = week.tuesday?.map { it.toAnimesInDay() }.orEmpty(),
            wednesday = week.wednesday?.map { it.toAnimesInDay() }.orEmpty(),
            thursday = week.thursday?.map { it.toAnimesInDay() }.orEmpty(),
            friday = week.friday?.map { it.toAnimesInDay() }.orEmpty(),
            saturday = week.saturday?.map { it.toAnimesInDay() }.orEmpty(),
            sunday = week.sunday?.map { it.toAnimesInDay() }.orEmpty()
        )
    }

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimesInDay
     */
    suspend fun getDay(day: Days): List<AnimesInDay> =
        Jikax.getSchedule(Day.valueOf(day.name)).map { it.toAnimesInDay() }

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimesInDay
     */
    suspend fun getAnime(name: String) =
        toAnimeInfoData(name, name.getJikaxInfo(), name.getMonksInfo())
    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimesInDay
     */
    suspend fun getAnime(malId: Int, name: String) =
        toAnimeInfoData(name, malId.getJikaxInfo(), name.getMonksInfo())

}