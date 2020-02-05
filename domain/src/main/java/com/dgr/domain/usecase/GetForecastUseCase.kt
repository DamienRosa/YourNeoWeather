package com.dgr.domain.usecase

import com.dgr.domain.repository.WeatherDomainRepository

class GetForecastUseCase(private val weatherDomainRepository: WeatherDomainRepository) {
    suspend operator fun invoke(city: String) = weatherDomainRepository.getForecast(city)
}