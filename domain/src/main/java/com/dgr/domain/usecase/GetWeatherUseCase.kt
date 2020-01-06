package com.dgr.domain.usecase

import com.dgr.domain.entity.City
import com.dgr.domain.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: City) = weatherRepository.getWeather(city)
}