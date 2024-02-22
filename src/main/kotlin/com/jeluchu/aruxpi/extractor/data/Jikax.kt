package com.jeluchu.aruxpi.extractor.data

import com.jeluchu.aruxpi.Aruxpi.getSearchAnime
import com.jeluchu.aruxpi.core.utils.RatcliffObershelp
import com.jeluchu.aruxpi.models.jikax.AnimeFullData
import com.jeluchu.aruxpi.models.search.AnimeSearch
import com.jeluchu.jikax.Jikax

suspend fun String.getJikaxInfo(): AnimeFullData? {
    var jikaxInfo: AnimeFullData? = null
    val jikan = findMostSimilarAnime(getSearchAnime(this), this)
    jikan?.let { data ->
        jikaxInfo = AnimeFullData(
            anime = Jikax.getAnime(data.malId),
            staff = Jikax.getStaff(data.malId),
            characters = Jikax.getCharacters(data.malId)
        )
    }

    return jikaxInfo
}

suspend fun Int.getJikaxInfo() = AnimeFullData(
    anime = Jikax.getAnime(this),
    staff = Jikax.getStaff(this),
    characters = Jikax.getCharacters(this)
)

fun findMostSimilarAnime(animeList: List<AnimeSearch>, query: String): AnimeSearch? {
    var maxSimilarity = 0.9
    val ro = RatcliffObershelp()
    var mostSimilarAnime: AnimeSearch? = null

    for (anime in animeList) {
        val similarity =
            ro.similarity(query, anime.title)
        if (similarity > maxSimilarity) {
            maxSimilarity = similarity
            mostSimilarAnime = anime
        }
    }

    return mostSimilarAnime
}

