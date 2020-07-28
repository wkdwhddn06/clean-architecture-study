package com.clean.news.domain.repositories

import com.clean.news.domain.model.News
import io.reactivex.rxjava3.core.Flowable

interface NewsRepository {
    fun getTopHeadlines(query: HeadLinesQuery): Flowable<News>
}

data class HeadLinesQuery(
    val country: String = "us"
)
