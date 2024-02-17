package com.jeluchu.aruxpi.models.anime

data class MoreInfo(
    //val aired: Aired,
    val broadcast: String,
    val duration: String,
    val endingThemes: List<String>,
    val licensors: List<String>,
    val openingThemes: List<String>,
    val producers: List<String>,
    val rank: Int,
    val source: String,
    val studios: List<String>,
    val titleJapanese: String,
    val totalEpisodes: Int
)