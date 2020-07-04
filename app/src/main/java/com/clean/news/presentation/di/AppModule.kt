package com.clean.news.presentation.di

import com.clean.news.data.api.NewsApi
import com.clean.news.data.model.News
import com.clean.news.data.network.NewsAuthInterceptor
import com.clean.news.data.network.httpClient
import com.clean.news.data.network.retrofitClient
import com.clean.news.data.repositories.NewsRepository
import com.clean.news.data.repositories.NewsRepositoryImpl
import com.clean.news.domain.common.AsyncFlowableTransformer
import com.clean.news.domain.usecase.NewsUseCase
import com.clean.news.presentation.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val mRepository = module {
    single(named(NEWS_REPOSITORY)) { NewsRepositoryImpl(get(named(NEWS_API))) as NewsRepository }
}

val mUseCases = module {
    single() {
        NewsUseCase(
            repository = get(named(NEWS_REPOSITORY)),
            transformer = AsyncFlowableTransformer<News>()
        )
    } //뉴스 UseCase
}

val mNetworkModules = module {
    single(named(NEWS_HTTP)) { httpClient(NewsAuthInterceptor()) }
    single(named(NEWS_CLIENT)) { retrofitClient(BASE_URL, get(named(NEWS_HTTP))) }
    single(named(NEWS_API)) { (get(named(NEWS_CLIENT)) as Retrofit).create(NewsApi::class.java) }
}

val mViewModels = module {
    viewModel {
        MainViewModel(get())
    }
}

const val BASE_URL = "https://newsapi.org/v2/"
const val NEWS_HTTP = "NEWS_HTTP"
const val NEWS_CLIENT = "NEWS_CLIENT"
const val NEWS_API = "NEWS_API"
const val NEWS_REPOSITORY = "NEWS_REPOSITORY"
