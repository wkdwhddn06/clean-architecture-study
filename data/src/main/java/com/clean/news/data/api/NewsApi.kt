package com.clean.news.data.api

import com.clean.news.domain.model.News
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApi {
    @GET("top-headlines")
    fun searchTopHeadlines(
        @QueryMap options : Map<String, String>
    ): Flowable<News>
}
