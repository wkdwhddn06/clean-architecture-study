package com.clean.news.domain.usecase

import com.clean.news.data.api.HeadLinesQuery
import com.clean.news.data.model.News
import com.clean.news.data.repositories.NewsRepository
import com.clean.news.domain.common.FlowableRxTransformer
import com.clean.news.domain.common.AsyncFlowableTransformer
import com.clean.news.domain.common.async
import io.reactivex.rxjava3.core.Flowable
import org.reactivestreams.Publisher

class NewsUseCase(
    private val transformer: FlowableRxTransformer<News>,
    private val repository: NewsRepository
) {
    fun getTopHeadlines(query: HeadLinesQuery = HeadLinesQuery()): Flowable<News> {
        return repository.getTopHeadlines(query).compose(transformer)
    }
}
