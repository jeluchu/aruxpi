package com.jeluchu.aruxpi.extractor

import com.jeluchu.aruxpi.core.utils.RatcliffObershelp
import com.jeluchu.aruxpi.models.jikax.AnimeFullData
import com.jeluchu.jikax.Jikax
import com.jeluchu.jikax.models.anime.AnimeData
import com.jeluchu.jikax.models.character.CharacterInfo
import com.jeluchu.jikax.models.staff.StaffInfo
import com.jeluchu.tioxime.Tioxime
import com.jeluchu.tioxime.models.anime.AnimeInfo
import com.jeluchu.tioxime.models.search.AnimeSearch

suspend fun String.getJikaxInfo(): AnimeFullData {
    lateinit var jikaxInfo: AnimeFullData
    val jikan = findMostSimilarAnime(Jikax.getSearchAnime(this), this)
    jikan?.let { data ->
        jikaxInfo = AnimeFullData(
            anime = Jikax.getAnime(data.malId),
            staff = Jikax.getStaff(data.malId),
            characters = Jikax.getCharacters(data.malId)
        )
    }

    return jikaxInfo
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

