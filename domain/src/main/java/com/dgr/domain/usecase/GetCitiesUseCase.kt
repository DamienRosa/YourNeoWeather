package com.dgr.domain.usecase

import com.dgr.domain.repository.CityDomainRepository

class GetCitiesUseCase(private val cityDomainRepository: CityDomainRepository) {
    suspend operator fun invoke() = cityDomainRepository.getCities()
}
