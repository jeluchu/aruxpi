package com.jeluchu.aruxpi.extractor

import com.jeluchu.aruxpi.core.utils.RatcliffObershelp
import com.jeluchu.monkx.Monkx
import com.jeluchu.monkx.models.anime.AnimeInfo
import com.jeluchu.monkx.models.search.AnimeSearch

suspend fun String.getMonksInfo(): AnimeInfo? {
    var monkxInfo: AnimeInfo? = null
    val monosChinos = findMostSimilarAnime(Monkx.getSearchAnime(this), this)
    monosChinos?.let {
        if (monosChinos.id.isNotEmpty()) {
            monkxInfo = Monkx.getAnime(monosChinos.id)
        }
    }
    return monkxInfo
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
