package com.dgr.data.api.repository

import com.dgr.data.api.helpers.Result
import com.dgr.data.api.model.WeatherResponse

interface IWeatherRepository {
    suspend fun getWeather(cityName: String): Result<WeatherResponse>
    suspend fun getForecast(cityName: String)
}