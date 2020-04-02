package com.dgr.data.repository

import com.dgr.data.db.WeatherAppDataBase
import com.dgr.data.db.entity.CityEntity
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.repository.CityDataSource

class CityWeatherDataRepository(private val dataBase: WeatherAppDataBase) : CityDataSource {

    override suspend fun getCities(): List<WeatherDomain> {
        val response = dataBase.getCityDao().getCities()
        return response.toDomainModel()
    }

    override suspend fun addCity(city: WeatherDomain) {
        dataBase.getCityDao().addCity(city.toDbModel())
    }

    override suspend fun removeCity(city: WeatherDomain) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateCity(city: WeatherDomain) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun WeatherDomain.toDbModel(): CityEntity =
        CityEntity(
            id = 0,
            city = this.city,
            weatherIcon = this.weatherIcon,
            temperature = this.temperature,
            lastUpdateDate = this.lastUpdateDate!!,
            humidity = this.humidity,
            country = this.country,
            description = this.description,
            pressure = this.pressure,
            sunrise = this.sunrise,
            sunset = this.sunset,
            visibility = this.visibility,
            windDirection = this.windDirection,
            windSpeed = this.windSpeed
        )

    private fun List<CityEntity>.toDomainModel(): List<WeatherDomain> =
        this.map { city -> WeatherDomain(
            city = city.city,
            country = city.country,
            description = city.description,
            windSpeed = city.windSpeed,
            windDirection = city.windDirection,
            temperature = city.temperature,
            humidity = city.humidity,
            pressure = city.pressure,
            visibility = city.visibility,
            sunrise = city.sunrise,
            sunset = city.sunset,
            weatherIcon = city.weatherIcon,
            lastUpdateDate = city.lastUpdateDate
        ) }
}
