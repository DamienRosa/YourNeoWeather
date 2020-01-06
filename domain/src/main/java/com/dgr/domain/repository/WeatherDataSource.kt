package com.dgr.domain.repository

import com.dgr.domain.entity.City
import com.dgr.domain.entity.ForecastData
import com.dgr.domain.entity.WeatherData

interface WeatherDataSource {
    suspend fun getWeather(city: City): WeatherData?
    suspend fun getForecast(city: City): ForecastData
}