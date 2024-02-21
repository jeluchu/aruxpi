package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.core.extensions.orZero
import com.jeluchu.aruxpi.core.extensions.toAnimesInDay
import com.jeluchu.aruxpi.core.extensions.toTopTime
import com.jeluchu.aruxpi.extractor.data.getJikaxInfo
import com.jeluchu.aruxpi.extractor.data.getMonksInfo
import com.jeluchu.aruxpi.extractor.data.toAnimeInfoData
import com.jeluchu.aruxpi.models.anime.AnimeInfoEntity
import com.jeluchu.aruxpi.models.schedule.AnimesInDay
import com.jeluchu.aruxpi.models.schedule.Days
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
     * @see AnimeInfoEntity
     */
    suspend fun getAnime(name: String) =
        toAnimeInfoData(name, name.getJikaxInfo(), name.getMonksInfo())

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimeInfoEntity
     */
    suspend fun getAnime(malId: Int, name: String) =
        toAnimeInfoData(name, malId.getJikaxInfo(), name.getMonksInfo())

    /**
     * Function to get all anime rankings.
     * @return List of anime in order by rank.
     */
    suspend fun getAnimeTop(
        top: String? = null,
        rating: String? = null,
        isCensored: Boolean? = null,
        page: Int = 1,
        limit: Int? = null
    ) = Jikax.getAnimeTop(
        filter = top,
        rating = rating,
        sfw = isCensored,
        page = page,
        limit = limit
    ).mapIndexed { index, anime ->
        anime.toTopTime(
            rank = index + 1,
            type = "anime",
            subtype = top.orEmpty(),
            page = page
        )
    }
}