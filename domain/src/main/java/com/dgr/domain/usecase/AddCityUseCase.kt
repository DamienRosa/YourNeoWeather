package com.dgr.domain.usecase

import com.dgr.domain.entity.CityWeather
import com.dgr.domain.repository.CityRepository

class AddCityUseCase(private val cityRepository: CityRepository) {
    suspend operator fun invoke(city: CityWeather) = cityRepository.addCity(city)
}