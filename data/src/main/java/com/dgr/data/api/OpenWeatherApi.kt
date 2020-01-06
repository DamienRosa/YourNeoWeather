package com.dgr.data.api

import com.dgr.data.api.model.WeatherResponse
import okhttp3.OkHttpClient
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
            val okHttpClient = createOkHttpClient(networkConnectionInterceptor)
            return createRetrofit(okHttpClient, BASE_API_URL)
        }

        private fun createRetrofit(httpClient: OkHttpClient, url: String): OpenWeatherApi =
            Retrofit.Builder()
                .client(httpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApi::class.java)

        private fun createOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
    }
}