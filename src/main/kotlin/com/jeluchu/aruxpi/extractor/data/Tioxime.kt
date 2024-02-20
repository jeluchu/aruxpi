package com.jeluchu.aruxpi.extractor.data

import com.jeluchu.aruxpi.core.utils.RatcliffObershelp
import com.jeluchu.tioxime.Tioxime
import com.jeluchu.tioxime.models.anime.AnimeInfo
import com.jeluchu.tioxime.models.search.AnimeSearch

suspend fun String.getTioximeInfo(): AnimeInfo? {
    var tioximeInfo: AnimeInfo? = null
    val tioAnime = findMostSimilarAnime(Tioxime.getSearchAnime(this), this)
    tioAnime?.let {
        if (tioAnime.id.isNotEmpty()) {
            tioximeInfo = Tioxime.getAnime(tioAnime.id)
        }
    }

    return tioximeInfo
}

fun findMostSimilarAnime(animeList: List<AnimeSearch>, query: String): AnimeSearch? {
    var maxSimilarity = 0.9
    val ro = RatcliffObershelp()
    var mostSimilarAnime: AnimeSearch? = null

    for (anime in animeList) {
        val similarity = ro.similarity(query, anime.title)
        if (similarity > maxSimilarity) {
            maxSimilarity = similarity
            mostSimilarAnime = anime
        }
    }

    return mostSimilarAnime
}

