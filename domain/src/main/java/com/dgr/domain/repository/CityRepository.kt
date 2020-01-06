package com.dgr.domain.repository

import com.dgr.domain.entity.CityWeather

class CityRepository(private val cityDataSource: CityDataSource) {
    suspend fun getCities(forcedLoad: Boolean) = cityDataSource.getCities(forcedLoad)

    suspend fun addCity(city: CityWeather) = cityDataSource.addCity(city)

    suspend fun removeCity(city: CityWeather) = cityDataSource.removeCity(city)

    suspend fun updateCity(city: CityWeather) = cityDataSource.updateCity(city)
}