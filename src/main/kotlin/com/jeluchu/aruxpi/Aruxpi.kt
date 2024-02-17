package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.core.extensions.toAnimesInDay
import com.jeluchu.aruxpi.core.utils.findMostSimilarAnime
import com.jeluchu.aruxpi.models.anime.AnimeInfoEntity
import com.jeluchu.aruxpi.models.schedule.AnimesInDay
import com.jeluchu.aruxpi.models.schedule.Week
import com.jeluchu.jikax.Jikax
import com.jeluchu.jikax.models.character.CharacterInfo
import com.jeluchu.jikax.models.schedule.Day
import com.jeluchu.jikax.models.staff.StaffInfo
import com.jeluchu.monkx.Monkx
import com.jeluchu.monkx.models.anime.AnimeInfo

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
        val monosChinos = findMostSimilarAnime(Monkx.getSearchAnime(name), name)
        var monkxInfo = AnimeInfo()
        monosChinos?.let {
            if (monosChinos.id.isNotEmpty()) {
                monkxInfo = Monkx.getAnime(monosChinos.id)
            }
        }

        val animeInfo = AnimeInfoEntity()
        val staff = mutableListOf<StaffInfo>()
        val characters = mutableListOf<CharacterInfo>()
        val jikan = findMostSimilarAnime(Jikax.getSearchAnime(name), name)
        jikan?.let { jikaxInfo ->
            staff.addAll(Jikax.getStaff(jikaxInfo.malId))
            characters.addAll(Jikax.getCharacters(jikaxInfo.malId))

            animeInfo.copy(
                malId = jikaxInfo.malId,
                title = jikaxInfo.titles?.first { it.type == "Default" }?.title.orEmpty(),
                poster = monkxInfo.image,
                cover = monkxInfo.cover,
                genres = monkxInfo.genres,
                synopsis = monkxInfo.synopsis,
                //episodes = monkxInfo.episodes,
                score = jikaxInfo.score.toString(),
                episodesCount = monkxInfo.episodesCount
            )
        }

        return animeInfo
    }
}