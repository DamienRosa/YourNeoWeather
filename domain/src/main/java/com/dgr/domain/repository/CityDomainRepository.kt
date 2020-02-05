package com.dgr.domain.repository

import com.dgr.domain.entity.WeatherDomain

class CityDomainRepository(private val cityDataSource: CityDataSource) {
    suspend fun getCities(forcedLoad: Boolean) = cityDataSource.getCities(forcedLoad)

    suspend fun addCity(city: WeatherDomain) = cityDataSource.addCity(city)

    suspend fun removeCity(city: WeatherDomain) = cityDataSource.removeCity(city)

    suspend fun updateCity(city: WeatherDomain) = cityDataSource.updateCity(city)
}