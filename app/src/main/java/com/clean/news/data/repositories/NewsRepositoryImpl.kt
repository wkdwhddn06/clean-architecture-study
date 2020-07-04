package com.clean.news.data.repositories

import com.clean.news.data.api.NewsApi
import com.clean.news.data.model.News
import io.reactivex.rxjava3.core.Flowable

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override fun getTopHeadlines(country: String): Flowable<News> {
        return api.searchTopHeadlines(country)
    }
}
