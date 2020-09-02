package com.dgr.domain.repository

import com.dgr.domain.entity.ForecastDomain
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.functional.Either
import com.dgr.domain.functional.FailureBo

interface WeatherDataSource {
    suspend fun getWeather(city: String): Either<FailureBo, WeatherDomain>
    suspend fun getForecast(city: String): ForecastDomain
}
