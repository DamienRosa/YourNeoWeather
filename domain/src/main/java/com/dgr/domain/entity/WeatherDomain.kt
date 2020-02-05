package com.dgr.domain.entity

data class WeatherDomain(
    val city : String,
    val country : String,
    val description : String,
    val temperature : Double,
    val pressure : Int,
    val humidity : Int,
    val windSpeed : Double,
    val windDirection: String,
    val visibility: Int,
    val sunset: Int,
    val sunrise: Int,
    val lastUpdateDate: String? = "",
    val weatherIcon: String? = ""
)