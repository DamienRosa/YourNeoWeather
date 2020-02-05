package com.dgr.domain.repository

import com.dgr.domain.entity.WeatherDomain

interface CityDataSource {
    suspend fun getCities(forceLoad: Boolean) : List<WeatherDomain>
    suspend fun addCity(city: WeatherDomain)
    suspend fun removeCity(city: WeatherDomain)
    suspend fun updateCity(city: WeatherDomain)
}