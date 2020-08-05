package com.clean.news.domain.common

import io.reactivex.rxjava3.core.SingleTransformer

abstract class SingleRxTransformer<T> : SingleTransformer<T, T>
