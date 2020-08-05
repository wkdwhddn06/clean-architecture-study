package com.clean.news.data.api

import com.clean.news.domain.model.News
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadlines(
        @QueryMap options: Map<String, String>
    ): Single<News>

    @GET("everything")
    fun getSearchResults(
        @QueryMap options: Map<String, String>
    ): Single<News>
}
