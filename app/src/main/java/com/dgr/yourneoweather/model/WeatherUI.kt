package com.dgr.yourneoweather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherUI(
    val city: String,
    val country: String,
    val description: String,
    val temperature: Int,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val visibility: Int,
    val sunset: Int,
    val sunrise: Int,
    val lastUpdateDate: String? = "",
    val weatherIcon: Int? = -1
) : Parcelable
