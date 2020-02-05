package com.dgr.domain.repository

import com.dgr.domain.entity.ForecastDomain
import com.dgr.domain.entity.WeatherDomain

interface WeatherDataSource {
    suspend fun getWeather(city: String): WeatherDomain?
    suspend fun getForecast(city: String): ForecastDomain
}