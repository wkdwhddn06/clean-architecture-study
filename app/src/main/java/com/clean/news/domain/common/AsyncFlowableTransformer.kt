package com.clean.news.domain.common

import com.clean.news.domain.common.FlowableRxTransformer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Publisher

class AsyncFlowableTransformer<T> : FlowableRxTransformer<T>() {
    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
