package com.dgr.data.api.repository

import com.dgr.data.api.OpenWeatherApi
import com.dgr.data.api.helpers.Result
import com.dgr.data.api.helpers.SafeAPIRequest
import com.dgr.data.api.model.WeatherResponse
import kotlinx.coroutines.coroutineScope

class RemoteWeatherRepository(private val api: OpenWeatherApi) : SafeAPIRequest(),
    IWeatherRepository {
    override suspend fun getWeather(cityName: String): Result<WeatherResponse> {
        return coroutineScope {
            apiRequest {
                api.getWeather(city = cityName)
            }
        }
    }

    override suspend fun getForecast(cityName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}