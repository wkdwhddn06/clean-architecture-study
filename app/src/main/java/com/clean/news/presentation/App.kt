package com.clean.news.presentation

import android.app.Application
import com.clean.news.presentation.di.mNetworkModules
import com.clean.news.presentation.di.mRepository
import com.clean.news.presentation.di.mUseCases
import com.clean.news.presentation.di.mViewModels
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mRepository, mNetworkModules, mUseCases, mViewModels)
        }

        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
