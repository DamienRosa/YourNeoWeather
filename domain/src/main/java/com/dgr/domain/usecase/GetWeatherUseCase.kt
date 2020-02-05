package com.dgr.domain.usecase

import com.dgr.domain.repository.WeatherDomainRepository

class GetWeatherUseCase(private val weatherDomainRepository: WeatherDomainRepository) {
    suspend operator fun invoke(city: String) = weatherDomainRepository.getWeather(city)
}