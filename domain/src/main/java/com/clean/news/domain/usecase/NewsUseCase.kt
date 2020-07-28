package com.clean.news.domain.usecase

import com.clean.news.domain.common.FlowableRxTransformer
import com.clean.news.domain.model.News
import com.clean.news.domain.repositories.HeadLinesQuery
import com.clean.news.domain.repositories.NewsRepository
import io.reactivex.rxjava3.core.Flowable

class NewsUseCase(
    private val transformer: FlowableRxTransformer<News>,
    private val repository: NewsRepository
) {
    fun getTopHeadlines(query: HeadLinesQuery = HeadLinesQuery()): Flowable<News> {
        return repository.getTopHeadlines(query).compose(transformer)
    }
}
