package com.clean.news.data.api

import com.clean.news.data.model.News
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun searchTopHeadlines(
        @Query("country") country: String
    ): Flowable<News>
}
