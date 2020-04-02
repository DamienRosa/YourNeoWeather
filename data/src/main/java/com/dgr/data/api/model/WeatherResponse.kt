package com.dgr.data.api.model

import com.dgr.domain.entity.WeatherDomain

data class WeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun WeatherResponse.toDomainModel(): WeatherDomain =
    WeatherDomain(
        city = this.name,
        country = this.sys.country,
        description = this.weather[0].description,
        windSpeed = this.wind.speed,
        windDirection = this.wind.deg,
        temperature = this.main.temp,
        humidity = this.main.humidity,
        pressure = this.main.pressure,
        visibility = this.visibility,
        sunrise = this.sys.sunrise,
        sunset = this.sys.sunset,
        weatherIcon = this.weather[0].id
    )
