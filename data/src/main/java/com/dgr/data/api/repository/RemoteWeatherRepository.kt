package com.dgr.data.api.repository

import com.dgr.data.api.OpenWeatherApi
import com.dgr.data.api.model.WeatherResponse
import kotlinx.coroutines.coroutineScope
import retrofit2.Response

class RemoteWeatherRepository(
    private val api: OpenWeatherApi
) : IWeatherRepository {
    override suspend fun getWeather(cityName: String): Response<WeatherResponse> =
        coroutineScope {
            api.getWeather(city = cityName)
        }


    override suspend fun getForecast(cityName: String) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
