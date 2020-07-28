package com.clean.news.presentation.network

import com.clean.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NewsAuthInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl =
            request.url.newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY).build()
        val newRequest = request.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}
