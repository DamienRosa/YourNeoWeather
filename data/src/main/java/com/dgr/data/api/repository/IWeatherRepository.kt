package com.dgr.data.api.repository

import com.dgr.data.api.model.WeatherResponse
import retrofit2.Response

interface IWeatherRepository {
    suspend fun getWeather(cityName: String): Response<WeatherResponse>
    suspend fun getForecast(cityName: String)
}
