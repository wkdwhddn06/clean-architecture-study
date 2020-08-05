package com.clean.news.domain.repositories

import com.clean.news.domain.common.Query
import com.clean.news.domain.common.QueryField
import com.clean.news.domain.model.News
import io.reactivex.rxjava3.core.Single

interface NewsRepository {
    fun getTopHeadlines(query: Query): Single<News>
    fun getSearchResults(query: Query): Single<News>
}

data class HeadLinesQuery(
    @QueryField("country") val country: String? = "us",
    @QueryField("q") val q: String? = null
) : Query

data class SearchQuery(
    @QueryField("q") val q: String? = null,
    @QueryField("language") val language: String? = "en",
    @QueryField("sortBy") val sortBy: String? = "relevancy"
) : Query
