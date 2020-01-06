package com.dgr.data.repository

import com.dgr.data.api.RemoteWeatherRepository
import com.dgr.data.api.Result
import com.dgr.data.api.model.toDomainModel
import com.dgr.data.db.LocalWeatherRepository
import com.dgr.domain.entity.City
import com.dgr.domain.entity.ForecastData
import com.dgr.domain.entity.WeatherData
import com.dgr.domain.repository.WeatherDataSource

class WeatherRepository(
    private val remoteWeatherRepository: RemoteWeatherRepository,
    private val localWeatherRepository: LocalWeatherRepository
) : WeatherDataSource {

    override suspend fun getWeather(city: City): WeatherData? {
        remoteWeatherRepository.getWeather(city.name).let {
            if (it is Result.Success) {
                return it.data.toDomainModel()
            }
        }
        return null
    }

    override suspend fun getForecast(city: City): ForecastData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}