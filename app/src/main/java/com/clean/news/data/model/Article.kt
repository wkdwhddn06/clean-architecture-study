package com.clean.news.data.model

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String, //TODO: Maybe Timestamp
    val content: String
)
