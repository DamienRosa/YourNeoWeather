package com.dgr.domain.usecase

import com.dgr.domain.repository.CityDomainRepository

class GetCitiesUseCase(private val cityDomainRepository: CityDomainRepository) {
    suspend operator fun invoke(forcedLoad: Boolean) = cityDomainRepository.getCities(forcedLoad)
}