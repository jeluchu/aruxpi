package com.jeluchu.aruxpi.core.utils

import com.jeluchu.jikax.models.anime.AnimeData
import com.jeluchu.monkx.models.search.AnimeSearch

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

fun findMostSimilarAnime(animeList: List<AnimeData>, query: String): AnimeData? {
    var maxSimilarity = 0.9
    val ro = RatcliffObershelp()
    var mostSimilarAnime: AnimeData? = null

    for (anime in animeList) {
        val similarity = ro.similarity(query, anime.titles?.first { it.type == "Default" }?.title.orEmpty())
        if (similarity > maxSimilarity) {
            maxSimilarity = similarity
            mostSimilarAnime = anime
        }
    }

    return mostSimilarAnime
}