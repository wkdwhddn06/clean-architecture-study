package com.clean.news.data.repository

import com.clean.news.data.api.NewsApi
import com.clean.news.domain.common.Query
import com.clean.news.domain.common.queryToMap
import com.clean.news.domain.model.News
import com.clean.news.domain.repositoriy.NewsRepository
import io.reactivex.rxjava3.core.Single

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override fun getTopHeadlines(query: Query): Single<News> {
        return api.getTopHeadlines(query.queryToMap())
    }

    override fun getSearchResults(query: Query): Single<News> {
        return api.getSearchResults(query.queryToMap())
    }
}
