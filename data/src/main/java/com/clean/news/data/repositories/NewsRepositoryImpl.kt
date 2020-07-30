package com.clean.news.data.repositories

import com.clean.news.data.api.NewsApi
import com.clean.news.domain.common.Query
import com.clean.news.domain.common.queryToMap
import com.clean.news.domain.model.News
import com.clean.news.domain.repositories.NewsRepository
import io.reactivex.rxjava3.core.Flowable

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override fun getTopHeadlines(query: Query): Flowable<News> {
        return api.getTopHeadlines(query.queryToMap())
    }

    override fun getSearchResults(query: Query): Flowable<News> {
        return api.getSearchResults(query.queryToMap())
    }
}
