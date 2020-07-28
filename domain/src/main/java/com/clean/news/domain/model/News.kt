package com.clean.news.domain.model

data class News (
    var status: String,
    var totalResults: Int,
    var articles: List<Article>
)
