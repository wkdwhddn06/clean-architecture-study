package com.clean.news.data.model

import com.clean.news.data.model.Article

data class News (
    var status: String,
    var totalResults: Int,
    var articles: List<Article>
)
