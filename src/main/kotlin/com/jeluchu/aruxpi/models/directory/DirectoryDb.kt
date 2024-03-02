package com.jeluchu.aruxpi.models.directory

data class DirectoryDb(
    val license: License?,
    val repository: String?,
    val lastUpdate: String?,
    val data: List<Source>?,
) {
    data class License(
        val name: String?,
        val url: String?
    )

    data class Source(
        val sources: List<String>?,
        val title: String?,
        val type: String?,
        val episodes: Int?,
        val status: String?,
        val animeSeason: AnimeSeason?,
        val picture: String?,
        val thumbnail: String?,
        val synonyms: List<String>?,
        val relatedAnime: List<String>?,
        val tags: List<String>?,
    )

    data class AnimeSeason(
        val season: String?,
        val year: Int?
    )
}