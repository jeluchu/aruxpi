package com.jeluchu.aruxpi.models.directory

data class Directory(
    val lastUpdate: String,
    val data: List<Anime>,
) {
    data class Anime(
        val mailId: Int,
        val title: String,
        val image: String
    )
}