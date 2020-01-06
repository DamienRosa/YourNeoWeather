package com.dgr.data.repository

import com.dgr.data.db.WeatherAppDataBase
import com.dgr.domain.entity.CityWeather
import com.dgr.domain.repository.CityDataSource

class CityWeatherRepository(private val dataBase: WeatherAppDataBase) : CityDataSource {

    override suspend fun getCities(forceLoad: Boolean): List<CityWeather> {
        val response = dataBase.getCityDao().getCities()

        return toMapper() ?: emptyList()
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

    private fun toMapper(): List<CityWeather>? = emptyList()
}


