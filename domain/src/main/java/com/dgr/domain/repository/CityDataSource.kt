package com.dgr.domain.repository

import com.dgr.domain.entity.WeatherDomain

interface CityDataSource {
    suspend fun getCities(): List<WeatherDomain>
    suspend fun addCity(city: WeatherDomain)
    suspend fun removeCity(city: WeatherDomain)
    suspend fun updateCity(city: WeatherDomain)
}
