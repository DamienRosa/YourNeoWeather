package com.dgr.data.api.helpers

import com.dgr.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url().newBuilder()
            .addQueryParameter("mode", "json")
            .addQueryParameter("units", "metric")
            .addQueryParameter("appid", BuildConfig.OpenWeatherApiKey)
            .build()

        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}