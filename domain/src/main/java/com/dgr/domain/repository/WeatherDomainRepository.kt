package com.dgr.domain.repository

class WeatherDomainRepository(private val weatherDataSource: WeatherDataSource) {
    suspend fun getWeather(city: String) = weatherDataSource.getWeather(city)
    suspend fun getForecast(city: String) = weatherDataSource.getForecast(city)
}
