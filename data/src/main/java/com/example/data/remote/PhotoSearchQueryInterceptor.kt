package com.example.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class PhotoSearchQueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder().run {
            addQueryParameter("nojsoncallback", "1")
            addQueryParameter("format", "json")
            addQueryParameter("api_key", "Your-api-key-here")
        }.build()
        val request = chain.request().newBuilder().url(newUrl).build()
        return chain.proceed(request)
    }
}