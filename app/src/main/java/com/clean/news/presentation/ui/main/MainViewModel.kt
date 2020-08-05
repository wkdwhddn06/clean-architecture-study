package com.clean.news.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import com.clean.news.domain.common.Query
import com.clean.news.domain.model.Article
import com.clean.news.domain.repositories.HeadLinesQuery
import com.clean.news.domain.repositories.SearchQuery
import com.clean.news.domain.usecase.NewsUseCase
import com.clean.news.presentation.common.BaseViewModel
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(private val newsUseCase: NewsUseCase) : BaseViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val articles = MutableLiveData<List<Article>>()
    val searchQuery = MutableLiveData<String>("")

    fun fetchNews() {
        val query: Query = if (searchQuery.value.isNullOrEmpty()) {
            HeadLinesQuery(country = "us")
        } else {
            SearchQuery(searchQuery.value)
        }

        val disposable = newsUseCase
            .getTopHeadlines(query)
            .subscribe({ response ->
                Logger.d("On Next Called: $response")
                if (response != null) {
                    articles.value = response.articles
                    Logger.d("Article num: ${articles.value!!.size}")
                } else {
                    Logger.d("Response is null!")
                }
            }, {
                Logger.d("On Error!: $it")
            })

        addDisposable(disposable)
    }

    fun getArticle(position: Int) = articles.value?.get(position)
}

const val NEWS_SERVICE = "NewsService"
