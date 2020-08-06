package com.clean.news.domain.usecase

import com.clean.news.domain.common.Query
import com.clean.news.domain.common.SingleRxTransformer
import com.clean.news.domain.model.News
import com.clean.news.domain.repositoriy.HeadLinesQuery
import com.clean.news.domain.repositoriy.NewsRepository
import com.clean.news.domain.repositoriy.SearchQuery
import io.reactivex.rxjava3.core.Single

class NewsUseCase(
    private val transformer: SingleRxTransformer<News>,
    private val repository: NewsRepository
) {
    fun getTopHeadlines(query: Query = HeadLinesQuery()): Single<News> {
        return repository.getTopHeadlines(query).compose(transformer)
    }

    fun getSearchResults(query: Query = SearchQuery()): Single<News> {
        return repository.getSearchResults(query).compose(transformer)
    }
}
