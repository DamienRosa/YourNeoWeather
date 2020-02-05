package com.dgr.yourneoweather.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherUI(
    val city: String,
    val country: String,
    val description: String,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: String,
    val visibility: Int,
    val sunset: Int,
    val sunrise: Int,
    val lastUpdateDate: String? = "",
    val weatherIcon: String? = ""
) : Parcelable