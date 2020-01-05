package com.dgr.domain.usecase

import com.dgr.domain.repository.CityRepository

class GetCitiesUseCase(private val cityRepository: CityRepository) {
    suspend operator fun invoke() = cityRepository.getCities()
}