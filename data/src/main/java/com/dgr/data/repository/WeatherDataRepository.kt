package com.dgr.data.repository

import com.dgr.data.api.model.toDomainModel
import com.dgr.data.api.repository.RemoteWeatherRepository
import com.dgr.data.db.repository.LocalWeatherRepository
import com.dgr.domain.entity.ForecastDomain
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.functional.Either
import com.dgr.domain.functional.FailureBo
import com.dgr.domain.repository.WeatherDataSource

class WeatherDataRepository(
    private val remoteWeatherRepository: RemoteWeatherRepository,
    private val localWeatherRepository: LocalWeatherRepository
) : WeatherDataSource {

    override suspend fun getWeather(city: String): Either<FailureBo, WeatherDomain> =
        safeApiCall(
            { remoteWeatherRepository.getWeather(city) },
            { it.toDomainModel() }
        )

    override suspend fun getForecast(city: String): ForecastDomain {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
