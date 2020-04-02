package com.dgr.data.api

import com.dgr.data.api.helpers.NetworkConnectionInterceptor
import com.dgr.data.api.model.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    suspend fun getWeather(@Query("q") city: String): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getForecast(@Query("q") city: String): Response<WeatherResponse>

    companion object {

        private const val BASE_API_URL: String = "http://api.openweathermap.org/data/2.5/"

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): OpenWeatherApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = createOkHttpClient(networkConnectionInterceptor, interceptor)
            return createRetrofit(okHttpClient)
        }

        private fun createRetrofit(httpClient: OkHttpClient): OpenWeatherApi =
            Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApi::class.java)

        private fun createOkHttpClient(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }
}
