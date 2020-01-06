package com.dgr.domain.repository

import com.dgr.domain.entity.CityWeather

interface CityDataSource {
    suspend fun getCities(forceLoad: Boolean) : List<CityWeather>
    suspend fun addCity(city: CityWeather)
    suspend fun removeCity(city: CityWeather)
    suspend fun updateCity(city: CityWeather)
}