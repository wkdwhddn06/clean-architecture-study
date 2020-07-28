package com.clean.news.domain.common

import io.reactivex.rxjava3.core.FlowableTransformer

abstract class FlowableRxTransformer<T>: FlowableTransformer<T, T>
