package com.clean.news.presentation

import android.app.Application
import android.content.Context
import com.clean.news.presentation.di.mNetworkModules
import com.clean.news.presentation.di.mRepository
import com.clean.news.presentation.di.mUseCases
import com.clean.news.presentation.di.mViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mRepository, mNetworkModules, mUseCases, mViewModels)
        }
    }
}
