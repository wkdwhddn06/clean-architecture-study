package com.clean.news.domain.common

import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class QueryField(val name: String)

fun Query.queryToMap(): Map<String, String> {
    return convert(this)
}

fun <T> T.convert(query: Query): Map<String, String> {
    val map: MutableMap<String, String> = mutableMapOf()
    val declaredMemberProperties = query::class.memberProperties

    for (prop in declaredMemberProperties) {
        val annotation = prop.findAnnotation<QueryField>()
        if (annotation != null) {
            val value: String? = prop.getter.call(query) as String?
            if (!value.isNullOrEmpty())
                map[annotation.name] = value
        }
    }

    return map
}
