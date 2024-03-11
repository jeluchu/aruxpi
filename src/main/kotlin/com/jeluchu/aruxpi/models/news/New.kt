package com.jeluchu.aruxpi.models.news

data class New(
    val source: String,
    val sourceDescription: String,
    val link: String,
    val title: String,
    val date: String,
    val description: String,
    val content: String,
    val image: String,
    val categories: List<String>
)