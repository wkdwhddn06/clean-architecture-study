package com.clean.news.domain.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()

fun <T> T.serializeToMap(): Map<String, String> {
    return convert()
}

fun <T, R> T.convert(): R {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<R>() {}.type)
}
