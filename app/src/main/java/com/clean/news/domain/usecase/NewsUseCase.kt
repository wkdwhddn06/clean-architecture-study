package com.clean.news.domain.usecase

import com.clean.news.data.model.News
import com.clean.news.data.repositories.NewsRepository
import com.clean.news.domain.common.FlowableRxTransformer
import io.reactivex.rxjava3.core.Flowable

class NewsUseCase(
    private val transformer: FlowableRxTransformer<News>,
    private val repository: NewsRepository
) {
    fun getTopHeadlines(country: String): Flowable<News> {
        return repository.getTopHeadlines(country).compose(transformer)
    }
}
