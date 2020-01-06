package com.dgr.domain.repository

import com.dgr.domain.entity.City

class WeatherRepository(private val weatherDataSource: WeatherDataSource) {
    suspend fun getWeather(city: City) = weatherDataSource.getWeather(city)
    suspend fun getForecast(city: City) = weatherDataSource.getForecast(city)
}
