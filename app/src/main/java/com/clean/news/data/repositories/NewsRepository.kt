package com.clean.news.data.repositories

import com.clean.news.data.model.News
import io.reactivex.rxjava3.core.Flowable

interface NewsRepository {
    fun getTopHeadlines(country: String): Flowable<News>
}
