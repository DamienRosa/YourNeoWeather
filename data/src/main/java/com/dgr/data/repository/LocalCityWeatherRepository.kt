package com.dgr.data.repository

import com.dgr.data.db.WeatherAppDataBase
import com.dgr.domain.entity.CityWeather
import com.dgr.domain.repository.CityDataSource

class LocalCityWeatherRepository(private val dataBase: WeatherAppDataBase) : CityDataSource {
    override suspend fun getCities(): List<CityWeather> {
        return mockedData()
    }

    override suspend fun addCity(city: CityWeather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeCity(city: CityWeather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateCity(city: CityWeather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mockedData(): List<CityWeather> = listOf(
        CityWeather(
            "asd",
            "asdC",
            23.0,
            "23",
            null,
            "12/12/12"
        ),
        CityWeather(
            "asd",
            "asdC",
            23.0,
            "23",
            null,
            "12/12/12"
        )
    )
}