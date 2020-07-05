package com.clean.news.data.repositories

import android.util.Log
import com.clean.news.data.api.HeadLinesQuery
import com.clean.news.data.api.NewsApi
import com.clean.news.data.model.News
import com.clean.news.domain.common.serializeToMap
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override fun getTopHeadlines(query: HeadLinesQuery): Flowable<News> {
        return api.searchTopHeadlines(query.serializeToMap())
    }
}
