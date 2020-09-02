package com.dgr.domain.repository

import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.functional.Either
import com.dgr.domain.functional.FailureBo

interface CityDataSource {
    suspend fun getCities(): Either<FailureBo, List<WeatherDomain>>
    suspend fun addCity(city: WeatherDomain): Either<FailureBo, Unit>
    suspend fun removeCity(city: WeatherDomain)
    suspend fun updateCity(city: WeatherDomain)
}
