package com.clean.news.data.repositories

import com.clean.news.data.api.NewsApi
import com.clean.news.domain.common.serializeToMap
import com.clean.news.domain.model.News
import com.clean.news.domain.repositories.HeadLinesQuery
import com.clean.news.domain.repositories.NewsRepository
import io.reactivex.rxjava3.core.Flowable

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override fun getTopHeadlines(query: HeadLinesQuery): Flowable<News> {
        return api.searchTopHeadlines(query.serializeToMap())
    }
}
