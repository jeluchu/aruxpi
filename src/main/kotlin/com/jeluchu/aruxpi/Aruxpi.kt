package com.jeluchu.aruxpi

import com.jeluchu.aruxpi.core.enums.Ratings
import com.jeluchu.aruxpi.core.enums.Seasons
import com.jeluchu.aruxpi.core.enums.Sources
import com.jeluchu.aruxpi.core.enums.TopStates
import com.jeluchu.aruxpi.core.extensions.orZero
import com.jeluchu.aruxpi.core.extensions.toAiringTime
import com.jeluchu.aruxpi.core.extensions.toAnimeSearch
import com.jeluchu.aruxpi.core.extensions.toAnimeSeason
import com.jeluchu.aruxpi.core.extensions.toAnimesInDay
import com.jeluchu.aruxpi.core.extensions.toEpisodeServer
import com.jeluchu.aruxpi.core.extensions.toRating
import com.jeluchu.aruxpi.core.extensions.toTopFilter
import com.jeluchu.aruxpi.core.extensions.toTopTime
import com.jeluchu.aruxpi.extractor.data.getJikaxInfo
import com.jeluchu.aruxpi.extractor.data.getMonksInfo
import com.jeluchu.aruxpi.extractor.data.toAnimeInfoData
import com.jeluchu.aruxpi.models.anime.AnimeInfoEntity
import com.jeluchu.aruxpi.models.episodes.toEpisodeItem
import com.jeluchu.aruxpi.models.images.toImagesEntity
import com.jeluchu.aruxpi.models.schedule.AnimesInDay
import com.jeluchu.aruxpi.models.schedule.Days
import com.jeluchu.aruxpi.models.schedule.Week
import com.jeluchu.aruxpi.models.search.AnimeSearch
import com.jeluchu.aruxpi.models.seasons.AllSeasonsYear
import com.jeluchu.aruxpi.models.seasons.AnimeSeason
import com.jeluchu.aruxpi.models.seasons.SeasonYear
import com.jeluchu.aruxpi.models.top.Top
import com.jeluchu.jikax.Jikax
import com.jeluchu.jikax.models.schedule.Day
import com.jeluchu.monkx.Monkx
import com.jeluchu.qwanx.Qwanx
import com.jeluchu.tioxime.Tioxime

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
        Jikax.getSchedule(Day.valueOf(day.name)).data.map { it.toAnimesInDay() }

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
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimeInfoEntity
     */
    suspend fun getRandomAnime(): AnimeInfoEntity {
        val random = Jikax.getRandomAnime()
        return getAnime(
            malId = random.malId.orZero(),
            name = random.titles?.first { it.type == "Default" }?.title.orEmpty()
        )
    }

    /**
     * Function to get all anime rankings.
     * @return List of anime in order by rank.
     */
    suspend fun getAnimeTop(
        top: TopStates? = null,
        rating: Ratings? = null,
        isCensored: Boolean? = null,
        page: Int = 1,
        limit: Int? = null
    ) = Jikax.getAnimeTop(
        filter = top.toTopFilter(),
        rating = rating?.toRating(),
        sfw = isCensored,
        page = page,
        limit = limit
    ).data.mapIndexed { index, anime ->
        anime.toTopTime(
            rank = index + 1,
            type = "anime",
            subtype = top?.name ?: TopStates.airing.name,
            page = page
        )
    }

    /**
     * Function to get search animes by name
     * @param name [String] Name of the anime.
     * @return Anime with all animes info.
     */
    suspend fun getAllAnimeTop(
        top: TopStates? = null,
        rating: Ratings? = null,
        isCensored: Boolean? = null,
        limit: Int? = null
    ): List<Top> {
        val response = Jikax.getAnimeTop(
            filter = top.toTopFilter(),
            rating = rating?.toRating(),
            sfw = isCensored,
            limit = limit,
        )

        var index = 0
        val animes = response.data.map{ anime ->
            index++
            anime.toTopTime(
                rank = index,
                type = "anime",
                subtype = top?.name ?: TopStates.airing.name,
                page = response.pagination.currentPage.orZero()
            )
        }

        return animes.toMutableList().apply {
            for (page in 2..(response.pagination.lastPage ?: 2)) {
                addAll(
                    Jikax.getAnimeTop(
                        filter = top.toTopFilter(),
                        rating = rating?.toRating(),
                        sfw = isCensored,
                        limit = limit,
                        page = page
                    ).data.map { anime ->
                        index++
                        anime.toTopTime(
                            rank = index,
                            type = "anime",
                            subtype = top?.name ?: TopStates.airing.name,
                            page = response.pagination.currentPage.orZero()
                        )
                    }
                )
            }
        }
    }

    /**
     * Function to get search animes by name
     * @param name [String] Name of the anime.
     * @return Anime with all animes info.
     */
    suspend fun getSearchAnime(name: String): List<AnimeSearch> {
        val response = Jikax.getSearchAnime(name)
        val animes = response.data.map { it.toAnimeSearch() }

        return animes.toMutableList().apply {
            for (page in 2..(response.pagination.lastPage ?: 2)) {
                addAll(
                    Jikax.getSearchAnime(
                        title = name,
                        page = page
                    ).data.map { it.toAnimeSearch() }
                )
            }
        }
    }

    /**
     * Function to get all animes in current season.
     * @return List of animes.
     */
    suspend fun getAllSeasonNow(): List<AnimeSeason> {
        val response = Jikax.getSeasonNow()
        val animes = response.data.map { it.toAnimeSeason() }

        return animes.toMutableList().apply {
            for (page in 2..(response.pagination.lastPage ?: 2)) {
                addAll(Jikax.getSeasonNow(page = page).data.map { it.toAnimeSeason() })
            }
        }
    }

    /**
     * Function to get all seasons.
     * @return List of all years with seasons.
     */
    suspend fun getSeasons(): List<SeasonYear> {
        val response = Jikax.getSeasons()
        val animes = response.data.map { it.toAiringTime() }

        return if (response.pagination.hasNextPage == true) {
            animes.toMutableList().apply {
                for (page in 2..(response.pagination.lastPage ?: 2)) {
                    addAll(Jikax.getSeasons().data.map { it.toAiringTime() })
                }
            }
        } else animes
    }

    /**
     * Function to get search animes by name
     * @param year [Int] Year of the season.
     * @param season [String] Name of the season.
     * @return Anime with all animes info.
     */
    suspend fun getSeason(year: Int, season: String): List<AnimeSeason> {
        val response = Jikax.getSeason(year, season)
        val animes = response.data.map { it.toAnimeSeason(year, season) }

        return animes.toMutableList().apply {
            for (page in 2..(response.pagination.lastPage ?: 2)) {
                addAll(
                    Jikax.getSeason(
                        year = year,
                        season = season,
                        page = page
                    ).data.map { it.toAnimeSeason(year, season) }
                )
            }
        }
    }

    /**
     * Function to get all animes seasons in year
     * @param year [Int] Year of the season.
     * @return All animes in all seasons by year.
     */
    suspend fun getAllSeasons(year: Int) = AllSeasonsYear(
        winter = getSeason(year, Seasons.winter.name),
        spring = getSeason(year, Seasons.spring.name),
        summer = getSeason(year, Seasons.summer.name),
        fall = getSeason(year, Seasons.fall.name),
    )

    /**
     * Function to get last episodes from MonosChinos
     * @return List of lasted animes.
     */
    suspend fun getLastEpisodes() =
        Monkx.getLastEpisodes().map { it.toEpisodeItem() }

    /**
     * Function to get servers by episode from MonosChinos
     * @return List of servers.
     */
    suspend fun getServers(id: String, sources: Sources) = when (sources) {
        Sources.Monkx -> Monkx.getServers(id).map { it.toEpisodeServer() }
        Sources.Tioxime -> Tioxime.getServers(id).map { it.toEpisodeServer() }
        else -> emptyList()
    }

    /**
     * Function to get all animes in current season.
     * @return List of animes.
     */
    suspend fun getImages(
        query: String,
        limit: Int = 50,
        locale: String = "es_ES"
    ) = Qwanx.getSearchImages(query, limit, locale).toImagesEntity(query)
}