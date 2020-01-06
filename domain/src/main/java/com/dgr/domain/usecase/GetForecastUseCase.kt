package com.dgr.domain.usecase

import com.dgr.domain.entity.City
import com.dgr.domain.repository.WeatherRepository

class GetForecastUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: City) = weatherRepository.getForecast(city)
}