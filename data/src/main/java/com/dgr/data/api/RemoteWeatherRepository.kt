package com.dgr.data.api

import com.dgr.data.api.model.WeatherResponse
import kotlinx.coroutines.coroutineScope

class RemoteWeatherRepository(private val api: OpenWeatherApi) : SafeAPIRequest() {
    suspend fun getWeather(cityName: String): Result<WeatherResponse> {
        return coroutineScope {
            apiRequest {
                api.getWeather(city = cityName)
            }
        }
    }

    suspend fun getForecast(cityName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}