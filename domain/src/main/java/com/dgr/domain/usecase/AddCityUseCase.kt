package com.dgr.domain.usecase

import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.repository.CityDomainRepository

class AddCityUseCase(private val cityDomainRepository: CityDomainRepository) {
    suspend operator fun invoke(city: WeatherDomain) = cityDomainRepository.addCity(city)
}
