package com.clean.news.presentation.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.clean.news.data.model.Article
import com.clean.news.domain.usecase.NewsUseCase
import com.clean.news.presentation.common.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import slush.ItemListEditor

class MainViewModel(private val newsUseCase: NewsUseCase) : BaseViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    var articles = MutableLiveData<List<Article>>()

    fun fetchNews() {
        val disposable = newsUseCase
            .getTopHeadlines("us")
            .subscribe({ response ->
                Log.d(NEWS_SERVICE, "On Next Called: $response")
                if (response != null) {
                    articles.value = response.articles
                    Log.d(NEWS_SERVICE, "Article num: ${articles.value!!.size}")
                } else {
                    Log.e(NEWS_SERVICE, "Response is null!")
                }
            }, { error ->
                Log.d(NEWS_SERVICE, "On Error Called: $error")
            }, {
                Log.d(NEWS_SERVICE, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getArticle(position: Int) = articles.value?.get(position)
}

const val NEWS_SERVICE = "NewsService"
